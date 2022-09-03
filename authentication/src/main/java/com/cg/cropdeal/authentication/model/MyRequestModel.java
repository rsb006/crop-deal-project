package com.cg.cropdeal.authentication.model;

public class MyRequestModel {
	protected String email;
	protected String password;
	protected String fullName;
	protected Boolean active;
	protected String roles;
	
	public MyRequestModel() {
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "AccountRequestModel{" +
			"email='" + email + '\'' +
			", password='" + password + '\'' +
			", fullName='" + fullName + '\'' +
			", active=" + active +
			", roles='" + roles + '\'' +
			'}';
	}
}
