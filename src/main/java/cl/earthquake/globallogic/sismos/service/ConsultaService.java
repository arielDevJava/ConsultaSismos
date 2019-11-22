package cl.earthquake.globallogic.sismos.service;

import java.time.LocalDate;

public interface ConsultaService {
    Object getSismosbyFecha(LocalDate fecIni, LocalDate fecFin);
    Object getSismosbyMagnitud(String minMag, String maxMag);
    Object getSismosByRangoFecha(LocalDate fechaInicioR1, LocalDate fechaTerminoR1, LocalDate fechaInicioR2, LocalDate fechaTerminoR2);
    Object getSismosByPais(String pais);
    Object getSismosByPaisFecha(String paisR1, String paisR2, LocalDate fechaR1, LocalDate fechaR2);
}
