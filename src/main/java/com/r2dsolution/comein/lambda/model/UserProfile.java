package com.r2dsolution.comein.lambda.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String ref_name;
//	private String firstname;
//	private String lastname;
	private String sub;
	private String comein_id;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String getFirstname() {
//		return firstname;
//	}
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//	public String getLastname() {
//		return lastname;
//	}
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	

	public String getComein_id() {
		return comein_id;
	}
	public void setComein_id(String comein_id) {
		this.comein_id = comein_id;
	}
	public String getRef_name() {
		return ref_name;
	}
	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}

	
	

}
