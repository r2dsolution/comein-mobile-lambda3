package com.r2dsolution.comein.lambda.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * POJO containing response object for API Gateway.
 */
public class GatewayResponse {

    //private final String body;
    private Map<String, Object> body = new HashMap<String,Object>();
    private Map<String, String> headers = new HashMap<String,String>();
    private final int statusCode;

   

    public Map<String, Object> getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public GatewayResponse(Map<String,Object> json_body) {
    	
    	Map<String,String> _headers = new HashMap<String,String>();
        _headers.put("Content-Type", "application/json");
        
        this.headers = Collections.unmodifiableMap(new HashMap<>(_headers));
        this.body = Collections.unmodifiableMap(new HashMap<>(json_body));
        this.statusCode = HttpStatus.SC_OK;
        
    }
    
    public GatewayResponse(Map<String,Object> json_body,int status) {
    	
    	Map<String,String> _headers = new HashMap<String,String>();
        _headers.put("Content-Type", "application/json");
        
        this.headers = Collections.unmodifiableMap(new HashMap<>(_headers));
        this.body = Collections.unmodifiableMap(new HashMap<>(json_body));
        this.statusCode = status;
        
    }
}
