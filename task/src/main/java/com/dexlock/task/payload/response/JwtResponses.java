package com.dexlock.task.payload.response;

import java.io.Serializable;

public class JwtResponses implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;



    public JwtResponses(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
