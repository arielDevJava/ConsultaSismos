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

@RestController
@RequestMapping("/consulta/sismos")
public class ConsultaController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ConsultaService consultaService;

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

    @GetMapping(path="/paises/fechas")
    @ResponseBody
    public ResponseEntity<Object> getSismosByPaisFecha(@RequestParam String paisR1, @RequestParam String paisR2,
                                       @RequestParam String fechaR1, @RequestParam String fechaR2){
        logger.info("getSismosByPaisFecha Rango Pais: "+ paisR1 +" "+paisR2);
        logger.info("getSismosByPaisFecha Rango Fecha: "+ fechaR1 +" "+fechaR2);

        try{
            LocalDate fecIniR1 = LocalDate.parse(fechaR1, formatter);
            LocalDate fecFinR1 = LocalDate.parse(fechaR2, formatter);

            return new ResponseEntity<>(consultaService.getSismosByPaisFecha(paisR1, paisR2, fecIniR1,  fecFinR1), HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Error al obtener sismos por paises y fechas [Exception]: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }


}
