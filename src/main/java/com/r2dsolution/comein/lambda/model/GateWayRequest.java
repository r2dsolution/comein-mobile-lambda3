package com.r2dsolution.comein.lambda.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GateWayRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> body = new HashMap<String,Object>();
	private UserProfile profile;
	private String env;
	private Map<String,Object> params= new HashMap<String,Object>();

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	
	public String getPath(String path) {
		Map<String,String> paths = (Map<String, String>) params.get("path");
		if (paths!=null && !path.isEmpty()) {
			return paths.get(path);
		}
		return null;
	}

}
