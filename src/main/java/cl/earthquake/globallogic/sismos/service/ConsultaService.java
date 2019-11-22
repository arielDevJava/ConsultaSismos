package cl.earthquake.globallogic.sismos.service;

import java.time.LocalDate;

public interface ConsultaService {
    Object getSismosbyFecha(LocalDate fecIni, LocalDate fecFin) throws Exception;
    Object getSismosbyMagnitud(String minMag, String maxMag) throws Exception;
    Object getSismosByRangoFecha(LocalDate fechaInicioR1, LocalDate fechaTerminoR1, LocalDate fechaInicioR2, LocalDate fechaTerminoR2) throws Exception;
    Object getSismosByPais(String pais) throws Exception;
    Object getSismosByPaisFecha(String paisR1, String paisR2, LocalDate fechaR1, LocalDate fechaR2) throws Exception;
}
