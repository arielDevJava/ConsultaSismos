package cl.earthquake.globallogic.sismos.controller;

import cl.earthquake.globallogic.sismos.service.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Clase que expone servicios rest para consulta de sismos
 */
@RestController
@RequestMapping("/consulta/sismos")
public class ConsultaController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ConsultaService consultaService;

    /**
     * Metodo que obtiene sismos dado un rango de fecha
     * @param starttime fecha inicial
     * @param endtime fecha final
     * @return ResponseEntity
     */
    @GetMapping(path="/fechas")
    @ResponseBody
    public ResponseEntity<Object> getSismosByFechas(@RequestParam String starttime, @RequestParam String endtime){
        logger.info("getSismosByFechas: "+ starttime + " "+ endtime);

        try{
            LocalDate fecIni = LocalDate.parse(starttime, formatter);
            LocalDate fecFin = LocalDate.parse(endtime, formatter);


            return new ResponseEntity<>(consultaService.getSismosbyFecha(fecIni, fecFin), HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por fechas [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }

    /**
     * Metodo que obtiene sismos dado un rango de magnitudes
     * @param minmagnitude magnitud minima
     * @param maxmagnitude magnitud maxima
     * @return ResponseEntity
     */
    @GetMapping(path="/magnitudes")
    @ResponseBody
    public ResponseEntity<Object> getSismosByMagnitudes(@RequestParam Double minmagnitude, @RequestParam Double maxmagnitude){
        logger.info("getSismosByMagnitudes: "+ minmagnitude + " "+maxmagnitude);

        try{
            DecimalFormatSymbols formatoSym = new DecimalFormatSymbols(Locale.getDefault());
            formatoSym.setDecimalSeparator('.');
            formatoSym.setGroupingSeparator(' ');

            DecimalFormat formatter = new DecimalFormat("#0.0", formatoSym);
            String minMag = formatter.format(minmagnitude);
            String maxMag = formatter.format(maxmagnitude);


            return new ResponseEntity<>(consultaService.getSismosbyMagnitud(minMag, maxMag), HttpStatus.OK);

        }
        catch(Exception e){
            logger.error("Error al obtener sismos por magnitud [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }


    }

    /**
     * Metodo que obtiene sismos dado un rango entre cuatro fechas
     * @param fechaInicioR1 fecha inicial rango 1
     * @param fechaTerminoR1 fecha final rango 1
     * @param fechaInicioR2 fecha inicial rango 2
     * @param fechaTerminoR2 fecha final rango 2
     * @return ResponseEntity
     */
    @GetMapping(path="/fechas/rango")
    @ResponseBody
    public ResponseEntity<Object> getSismosByRango(@RequestParam String fechaInicioR1, @RequestParam String fechaTerminoR1,
                                   @RequestParam String fechaInicioR2, @RequestParam String fechaTerminoR2){
        logger.info("getSismosByRango R1: "+ fechaInicioR1 + " "+ fechaTerminoR1);
        logger.info("getSismosByRango R2: "+ fechaInicioR2 + " "+ fechaTerminoR2);

        try{
            LocalDate fecIniR1 = LocalDate.parse(fechaInicioR1, formatter);
            LocalDate fecFinR1 = LocalDate.parse(fechaTerminoR1, formatter);
            LocalDate fecIniR2 = LocalDate.parse(fechaInicioR2, formatter);
            LocalDate fecFinR2 = LocalDate.parse(fechaTerminoR2, formatter);

            return new ResponseEntity<>(consultaService.getSismosByRangoFecha(fecIniR1, fecFinR1, fecIniR2, fecFinR2), HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por rango de fechas [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }

    /**
     * Metodo que retorna sismos dado un pais
     * @param pais nombre pais
     * @return ResponseEntity
     */
    @GetMapping(path="/pais")
    @ResponseBody
    public ResponseEntity<Object> getSismosByPais(@RequestParam String pais){
        logger.info("getSismosByPais: "+ pais);
        try{

            return new ResponseEntity<>(consultaService.getSismosByPais(pais), HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por pais [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }

    /**
     * Metodo que obtiene sismos dado paises y un rango de fecha
     * @param paisR1 nombre pais rango 1
     * @param paisR2 nombre pais rango 2
     * @param starttime fecha inicial
     * @param endtime fecha final
     * @return ResponseEntity
     */
    @GetMapping(path="/paises/fechas")
    @ResponseBody
    public ResponseEntity<Object> getSismosByPaisFecha(@RequestParam String paisR1, @RequestParam String paisR2,
                                       @RequestParam String starttime, @RequestParam String endtime){
        logger.info("getSismosByPaisFecha Rango Pais: "+ paisR1 +" "+paisR2);
        logger.info("getSismosByPaisFecha Rango Fecha: "+ starttime +" "+endtime);

        try{
            LocalDate fecIniR1 = LocalDate.parse(starttime, formatter);
            LocalDate fecFinR1 = LocalDate.parse(endtime, formatter);

            return new ResponseEntity<>(consultaService.getSismosByPaisFecha(paisR1, paisR2, fecIniR1,  fecFinR1), HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por paises y fechas [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }


}
