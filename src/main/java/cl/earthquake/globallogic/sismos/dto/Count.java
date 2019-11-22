package cl.earthquake.globallogic.sismos.dto;

import java.io.Serializable;

public class Count implements Serializable {

    private Integer count;
    private Integer maxAllowed;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(Integer maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

}
