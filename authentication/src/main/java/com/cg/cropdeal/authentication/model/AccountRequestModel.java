package com.cg.cropdeal.authentication.model;

public class AccountRequestModel {
	protected String email;
	protected String password;
	protected String fullName;
	protected Boolean active;
	protected String roles;

	public AccountRequestModel () {
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public void setFullName (String fullName) {
		this.fullName = fullName;
	}

	public void setActive (Boolean active) {
		this.active = active;
	}

	public void setRoles (String roles) {
		this.roles = roles;
	}

	@Override
	public String toString () {
		return "AccountRequestModel{" +
		 "email='" + email + '\'' +
		 ", password='" + password + '\'' +
		 ", fullName='" + fullName + '\'' +
		 ", active=" + active +
		 ", roles='" + roles + '\'' +
		 '}';
	}
}
