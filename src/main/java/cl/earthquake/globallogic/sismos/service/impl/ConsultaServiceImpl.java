package cl.earthquake.globallogic.sismos.service.impl;

import cl.earthquake.globallogic.sismos.dto.Count;
import cl.earthquake.globallogic.sismos.dto.Data;
import cl.earthquake.globallogic.sismos.dto.Features;
import cl.earthquake.globallogic.sismos.dto.Metadata;
import cl.earthquake.globallogic.sismos.service.ConsultaService;
import cl.earthquake.globallogic.sismos.util.Constantes;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que consulta api externa de sismos
 */
@Service
public class ConsultaServiceImpl implements ConsultaService {
    private static final Logger logger = LoggerFactory.getLogger(ConsultaServiceImpl.class);
    private static final Integer MAXALLOWED = 20000;

    @Autowired
    RestTemplate restTemplate;

    @Value("${earthquake.endpoint.query}")
    private String urlQuery;

    @Value("${earthquake.endpoint.count}")
    private String urlCount;

    @Value("${format.type}")
    private String formatType;

    @Override
    public Object getSismosbyFecha(LocalDate fecIni, LocalDate fecFin) throws Exception {
        logger.info("getSismosbyFecha "+ fecIni +" "+ fecFin);
        Gson gson = new Gson();
        try{
            ResponseEntity<String> response = restTemplate.exchange(generateBuilder(urlQuery, fecIni, fecFin, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            return gson.fromJson(response.getBody(), Object.class);

        }
        catch(Exception e){
            logger.error("Error al obtener sismos por fecha ,Exception: "+ e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Object getSismosbyMagnitud(String minMag, String maxMag) throws Exception {
        logger.info("getSismosbyMagnitud "+ minMag +" "+ maxMag);
        Gson gson = new Gson();

        try{
            ResponseEntity<String> response = restTemplate.exchange(generateBuilder(urlQuery, null, null, minMag, maxMag).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            return gson.fromJson(response.getBody(), Object.class);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por magnitudes ,Exception: "+ e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Object getSismosByRangoFecha(LocalDate fechaInicioR1, LocalDate fechaTerminoR1, LocalDate fechaInicioR2, LocalDate fechaTerminoR2) throws Exception {
        logger.info("getSismosByRangoFecha: "+fechaInicioR1 +" "+fechaTerminoR1 +" "+fechaInicioR2 +" "+fechaTerminoR2);
        Gson gson = new Gson();
        String result = "";

        try{
            ResponseEntity<String> responseR1 = restTemplate.exchange(generateBuilder(urlQuery, fechaInicioR1, fechaTerminoR1, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);
            ResponseEntity<String> responseR2 = restTemplate.exchange(generateBuilder(urlQuery, fechaInicioR2, fechaTerminoR2, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            if(responseR1 != null && responseR2 != null){
                Data dataFecR1 = gson.fromJson(responseR1.getBody(), Data.class);
                Data dataFecR2 = gson.fromJson(responseR2.getBody(), Data.class);

                Features[] features = ArrayUtils.addAll(dataFecR1.getFeatures(), dataFecR2.getFeatures());

                List<Features> list = Arrays.asList(features);

                logger.debug("list "+list.size());

                Set<Features> set = list.stream()
                        .collect(Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(Features::getId))));

                logger.debug("set "+set.size());

                Data data = getData(dataFecR1, set.size());

                data.setFeatures(set.stream().toArray(Features[]::new));

                result = gson.toJson(data, Object.class);
            }

            return gson.fromJson(result, Object.class);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por rango de fechas ,Exception: "+ e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Object getSismosByPais(String pais) throws Exception {
        logger.info("getSismosByPais: "+ pais);
        Gson gson = new Gson();
        String result = "";
        try{
            ResponseEntity<String> response = restTemplate.exchange(generateBuilder(urlQuery, null, null, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            if(response != null){
                Data dataAll = gson.fromJson(response.getBody(), Data.class);

                List<Features> list = Arrays.asList(dataAll.getFeatures());
                List<Features> listAux = new ArrayList<>();
                list.forEach(f ->{
                    if(f.getProperties().getPlace().toUpperCase().contains(pais.toUpperCase())){
                        listAux.add(f);
                    }
                });

                logger.debug("listAux "+listAux.size());

                Data data = getData(dataAll, listAux.size());

                data.setFeatures(listAux.stream().toArray(Features[]::new));

                result = gson.toJson(data, Object.class);

            }

            return gson.fromJson(result, Object.class);

        }
        catch(Exception e){
            logger.error("Error al obtener sismos por pais ,Exception: "+ e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Object getSismosByPaisFecha(String paisR1, String paisR2, LocalDate fechaR1, LocalDate fechaR2) throws Exception {
        Gson gson = new Gson();
        int countPais;
        String result = "";
        try{
            ResponseEntity<String> responsePais = restTemplate.exchange(generateBuilder(urlQuery, null, null, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            ResponseEntity<String> responseFec = restTemplate.exchange(generateBuilder(urlCount, fechaR1, fechaR2, null, null).toUriString(),
                    HttpMethod.GET, generateEntity(), String.class);

            if(responsePais != null && responseFec != null){
                Data dataAll = gson.fromJson(responsePais.getBody(), Data.class);

                List<Features> list = Arrays.asList(dataAll.getFeatures());
                List<Features> listAux = new ArrayList<>();

                if(!paisR1.toUpperCase().equals(paisR2.toUpperCase())){
                    list.forEach(f ->{
                        if(f.getProperties().getPlace().toUpperCase().contains(paisR1.toUpperCase()) ||
                                f.getProperties().getPlace().toUpperCase().contains(paisR2.toUpperCase())){
                            listAux.add(f);
                        }
                    });
                }
                else{
                    list.forEach(f ->{
                        if(f.getProperties().getPlace().toUpperCase().contains(paisR1.toUpperCase())){
                            listAux.add(f);
                        }
                    });
                }

                countPais = listAux.size();
                Count countFec = gson.fromJson(responseFec.getBody(), Count.class);

                Count countTotal = new Count();
                countTotal.setCount(countFec.getCount() + countPais);
                countTotal.setMaxAllowed(MAXALLOWED);

                result = gson.toJson(countTotal);
            }

            return gson.fromJson(result, Object.class);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por paises y fechas ,Exception: "+ e.getMessage());
            throw new Exception(e.getMessage());
        }


    }

    /**
     * Metodo que obtiene el componeent builder
     * @param url url del servicio
     * @param fechaR1 fecha inicial
     * @param fechaR2 fecha final
     * @param minMag magnitud minima
     * @param maxMag magnitud maxima
     * @return UriComponentsBuilder
     */
    private UriComponentsBuilder generateBuilder(String url, LocalDate fechaR1, LocalDate fechaR2, String minMag, String maxMag){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if(fechaR1 != null && fechaR2 != null){
            builder.queryParam(Constantes.FORMAT, formatType)
                    .queryParam(Constantes.START_TIME, fechaR1)
                    .queryParam(Constantes.END_TIME, fechaR2);
        }
        else if(minMag != null & maxMag != null){
            builder.queryParam(Constantes.FORMAT, formatType)
                    .queryParam(Constantes.MIN_MAGNITUDE, minMag)
                    .queryParam(Constantes.MAX_MAGNITUDE, maxMag);
        }
        else{
            builder.queryParam(Constantes.FORMAT, formatType);
        }

        return builder;

    }

    /**
     * Metodo que genera cabecera http
     * @return HttpEntity
     */
    private HttpEntity<String> generateEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return entity;
    }

    /**
     * Metodo que setea Objeto Data
     * @param dataAll datos extraidos de servicio
     * @param size tamaño data
     * @return Objeto Data
     */
    private Data getData(Data dataAll, int size){
        Data data = new Data();
        Metadata metadata = new Metadata();
        metadata.setGenerated(dataAll.getMetadata().getGenerated());
        metadata.setUrl(dataAll.getMetadata().getUrl());
        metadata.setTitle(dataAll.getMetadata().getTitle());
        metadata.setStatus(dataAll.getMetadata().getStatus());
        metadata.setApi(dataAll.getMetadata().getApi());
        metadata.setCount(size);

        data.setType(dataAll.getType());
        data.setMetadata(metadata);

        return data;
    }
}
