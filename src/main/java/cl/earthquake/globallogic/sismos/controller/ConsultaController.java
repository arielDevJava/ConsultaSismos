package cl.earthquake.globallogic.sismos.controller;

import cl.earthquake.globallogic.sismos.service.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/consulta/sismos")
public class ConsultaController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    @Autowired
    private ConsultaService consultaService;

    @GetMapping(path="/fechas")
    @ResponseBody
    public String getSismosByFechas(@RequestParam String starttime, @RequestParam String endtime){
        logger.info("getSismosByFechas: "+ starttime + " "+ endtime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fecIni = LocalDate.parse(starttime, formatter);
        LocalDate fecFin = LocalDate.parse(endtime, formatter);

        return null;
    }

    @GetMapping(path="/magnitudes")
    @ResponseBody
    public String getSismosByMagnitudes(@RequestParam Double minmagnitude, @RequestParam Double maxmagnitude){
        logger.info("getSismosByMagnitudes: "+ minmagnitude + " "+maxmagnitude);
        DecimalFormat formatter = new DecimalFormat("#0.0");
        String minMag = formatter.format(minmagnitude);
        String maxMag = formatter.format(maxmagnitude);

        return null;

    }

    @GetMapping(path="/fechas/rango")
    @ResponseBody
    public String getSismosByRango(@RequestParam String fechaInicioR1, @RequestParam String fechaTerminoR1,
                                   @RequestParam String fechaInicioR2, @RequestParam String fechaTerminoR2){
        logger.info("getSismosByRango R1: "+ fechaInicioR1 + " "+ fechaTerminoR1);
        logger.info("getSismosByRango R2: "+ fechaInicioR2 + " "+ fechaTerminoR2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fecIniR1 = LocalDate.parse(fechaInicioR1, formatter);
        LocalDate fecFinR1 = LocalDate.parse(fechaTerminoR1, formatter);
        LocalDate fecIniR2 = LocalDate.parse(fechaInicioR2, formatter);
        LocalDate fecFinR2 = LocalDate.parse(fechaTerminoR2, formatter);

        return null;
    }

    @GetMapping(path="/pais")
    @ResponseBody
    public String getSismosByPais(@RequestParam String pais){
        logger.info("getSismosByPais: "+ pais);

        return null;

    }

    @GetMapping(path="/paises/fechas")
    @ResponseBody
    public String getSismosByPaisFecha(@RequestParam String paisR1, @RequestParam String paisR2,
                                       @RequestParam String fechaR1, @RequestParam String fechaR2){
        logger.info("getSismosByPaisFecha R1: "+ paisR1 +" "+fechaR1);
        logger.info("getSismosByPaisFecha R2: "+ paisR2 +" "+fechaR2);

        return null;

    }


}
