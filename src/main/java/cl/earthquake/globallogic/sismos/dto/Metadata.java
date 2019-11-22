package cl.earthquake.globallogic.sismos.dto;

import java.io.Serializable;

public class Metadata implements Serializable {
    private Double generated;
    private String url;
    private String title;
    private Integer status;
    private String api;
    private Integer count;

    public Double getGenerated() {
        return generated;
    }

    public void setGenerated(Double generated) {
        this.generated = generated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
