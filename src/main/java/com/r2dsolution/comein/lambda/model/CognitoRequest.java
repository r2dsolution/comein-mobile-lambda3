package com.r2dsolution.comein.lambda.model;

import java.util.HashMap;
import java.util.Map;

import com.r2dsolution.comein.cognito.model.DatasetRecord;

public class CognitoRequest {
	
	private String eventType;
	private String  region;
	private String identityPoolId;
	private String identityId;
	private String datasetName;
	private Map<String,DatasetRecord> datasetRecords = new HashMap<String,DatasetRecord>();
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getIdentityPoolId() {
		return identityPoolId;
	}
	public void setIdentityPoolId(String identityPoolId) {
		this.identityPoolId = identityPoolId;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getDatasetName() {
		return datasetName;
	}
	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}
	public Map<String, DatasetRecord> getDatasetRecords() {
		return datasetRecords;
	}
	public void setDatasetRecords(Map<String, DatasetRecord> datasetRecords) {
		this.datasetRecords = datasetRecords;
	}
	
	
	

}
