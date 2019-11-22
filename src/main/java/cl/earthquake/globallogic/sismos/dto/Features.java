package cl.earthquake.globallogic.sismos.dto;

import java.io.Serializable;

/**
 * clase que setea caracteristicas de los sismos
 */
public class Features implements Serializable {
    private String type;
    private Properties properties;
    private Geometry geometry;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
