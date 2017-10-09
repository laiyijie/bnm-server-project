package cn.bangnongmang.admin.data.combo.domain;

import java.io.Serializable;

/**
 * Created by admin on 2017-05-10.
 */
public class RescAndMethod implements Serializable {
    public static final String ALL_METHOD = "ALL";
    public static final String GET_METHOD = "GET";
    private String resource;
    private String method;


    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
