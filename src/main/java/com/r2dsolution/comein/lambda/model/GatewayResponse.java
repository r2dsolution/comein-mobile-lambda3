package com.r2dsolution.comein.lambda.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * POJO containing response object for API Gateway.
 */
public class GatewayResponse {

    private final String body;
    private final Map<String, String> headers;
    private final int statusCode;

    public GatewayResponse(final String body, final Map<String, String> headers, final int statusCode) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public GatewayResponse(Map<String,Object> json_body) {
    	 Map<String, String> headers = new HashMap<>();
         headers.put("Content-Type", "application/json");
        this.statusCode = 200;
        headers.put("Content-Type", "application/json");
        this.body = map2json(json_body);
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }
    
    public GatewayResponse(Map<String,Object> json_body, final Map<String, String> headers, final int statusCode) {
        this.statusCode = statusCode;
        headers.put("Content-Type", "application/json");
        this.body = map2json(json_body);
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }
    protected String map2json(Map<String,Object> json_body) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return mapper.writeValueAsString(json_body);
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		return "INVALID JSON";
    	}
    	
    }
}
