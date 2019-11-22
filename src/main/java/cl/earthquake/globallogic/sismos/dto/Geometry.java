package cl.earthquake.globallogic.sismos.dto;

import java.io.Serializable;

/**
 * clase que setea datos de ubicacion
 */
public class Geometry implements Serializable {
    private String type;
    private String[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }
}
