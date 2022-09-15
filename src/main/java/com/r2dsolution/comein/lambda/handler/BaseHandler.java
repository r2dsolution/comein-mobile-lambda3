package com.r2dsolution.comein.lambda.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2dsolution.comein.config.ComeInConfig;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.lambda.model.GatewayResponse;

public abstract class BaseHandler<T extends GateWayRequest> implements RequestHandler<T,GatewayResponse>{
	
	protected ApplicationContext ctx;
	protected LambdaLogger lambdaLogger;
	protected ObjectMapper mapper = new ObjectMapper();

	@Override
	public GatewayResponse handleRequest(T input, Context context) {
		try {
//			 cacheFactory = new HashMap<Class,Map>();
//			 mapper = new ObjectMapper();
			 lambdaLogger = context.getLogger();
			 
			 ctx = new AnnotationConfigApplicationContext(ComeInConfig.class);
			 
			
	        Map<String,Object> output = new HashMap<String,Object>();
	        output = doHandlerRequest(input,output,context);
	      //  String json = toJson(output);
	      //  JsonNode node = toJsonNode(output);
//	        Map<String, Object> results = toJsonMap(output);
	        log("json-result: "+toJson(output));
	        return new GatewayResponse(output);
		} catch(Exception ex) {
			log("error: "+ex.getMessage());
			Map<String,Object> errors = new HashMap<String,Object>();
			errors.put("error", ex.getMessage());
			return new GatewayResponse(errors,HttpStatus.SC_EXPECTATION_FAILED);
		}
	}
	abstract protected Map<String, Object> doHandlerRequest(T input, Map<String, Object> output,Context context) throws Exception ;
	
	protected Map<String,String> initHeaders(){
		Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
	}

	protected void log(String message) {
		lambdaLogger.log(message+"\n");
	}
	
	protected String toJson(Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}
	
	protected Map<String, Object> toJsonMap(Object obj){
		JsonNode nodes = mapper.valueToTree(obj);
		TypeReference typeRef = new TypeReference<Map<String, Object>>(){};
		Map<String, Object> result = mapper.convertValue(nodes, typeRef);
		return result;
	}
}
