package cl.earthquake.globallogic.sismos.service.impl;

import cl.earthquake.globallogic.sismos.service.ConsultaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsultaServiceImpl implements ConsultaService {
    @Override
    public String getSismosbyFecha(LocalDate fecIni, LocalDate fecFin) {
        return null;
    }

    @Override
    public String getSismosbyMagnitud(String minMag, String maxMag) {
        return null;
    }

    @Override
    public String getSismosByRangoFecha(String fechaInicioR1, String fechaTerminoR1, String fechaInicioR2, String fechaTerminoR2) {
        return null;
    }

    @Override
    public String getSismosByPais(String pais) {
        return null;
    }

    @Override
    public String getSismosByPaisFecha(String paisR1, String paisR2, String fechaR1, String fechaR2) {
        return null;
    }
}
