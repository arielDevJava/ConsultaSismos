package cl.earthquake.globallogic.sismos.service;

import java.time.LocalDate;

public interface ConsultaService {
    String getSismosbyFecha(LocalDate fecIni, LocalDate fecFin);
    String getSismosbyMagnitud(String minMag, String maxMag);
    String getSismosByRangoFecha(String fechaInicioR1, String fechaTerminoR1, String fechaInicioR2, String fechaTerminoR2);
    String getSismosByPais(String pais);
    String getSismosByPaisFecha(String paisR1, String paisR2, String fechaR1, String fechaR2);
}
