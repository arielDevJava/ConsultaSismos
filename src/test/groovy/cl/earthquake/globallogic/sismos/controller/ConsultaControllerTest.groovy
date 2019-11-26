package cl.earthquake.globallogic.sismos.controller

import cl.earthquake.globallogic.sismos.service.impl.ConsultaServiceImpl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

class ConsultaControllerTest extends Specification {
    def consultaController = new ConsultaController()

    MockMvc mockMvc = standaloneSetup(consultaController).build()

    @Autowired
    private ConsultaServiceImpl consultaService

    def "GetSismosByFechas"() {

        when:
        def results = mockMvc.perform(get('/consulta/sismos/fechas').param('starttime', '2019-10-13')
                .param('endtime', '2019-10-14'))
                .andReturn().getResponse()

        then:
        results.status == HttpStatus.NOT_IMPLEMENTED.value()

    }

    def "GetSismosByMagnitudes"() {
        when:
        def results = mockMvc.perform(get('/consulta/sismos/magnitudes').param('minmagnitude', '5.0')
                .param('maxmagnitude', '6.0'))
                .andReturn().getResponse()

        then:
        results.status == HttpStatus.NOT_IMPLEMENTED.value()
    }

    def "GetSismosByRango"() {
        when:
        def results = mockMvc.perform(get('/consulta/sismos/fechas/rango').param('fechaInicioR1', '2019-10-13')
                .param('fechaTerminoR1', '2019-10-14')
                .param('fechaInicioR2', '2019-10-15')
                .param('fechaTerminoR2', '2019-10-16'))
                .andReturn().getResponse()

        then:
        results.status == HttpStatus.NOT_IMPLEMENTED.value()
    }

    def "GetSismosByPais"() {
        when:
        def results = mockMvc.perform(get('/consulta/sismos/pais').param('pais', 'chile'))
                .andReturn().getResponse()

        then:
        results.status == HttpStatus.NOT_IMPLEMENTED.value()
    }

    def "GetSismosByPaisFecha"() {
        when:
        def results = mockMvc.perform(get('/consulta/sismos/paises/fechas').param('paisR1', 'chile')
                .param('paisR2', 'alaska')
                .param('starttime', '2019-10-15')
                .param('endtime', '2019-10-16'))
                .andReturn().getResponse()

        then:
        results.status == HttpStatus.NOT_IMPLEMENTED.value()
    }

}
