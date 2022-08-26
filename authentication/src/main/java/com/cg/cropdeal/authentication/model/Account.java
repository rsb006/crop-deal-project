package com.cg.cropdeal.authentication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private Boolean active;
	private String roles;

	public Account () {

	}

	public Account (AccountRequestModel req) {
		this.userName = req.email;
		this.password = req.password;
		this.fullName = req.fullName;
		this.active = req.active;
		this.roles = req.roles;
	}

	public Account (String userName, String password, String fullName) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	public Account (String userName, String password, String fullName, Boolean active, String roles) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.active = active;
		this.roles = roles;
	}


	public String getUserName () {
		return userName;
	}

	public void setUserName (String userName) {
		this.userName = userName;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public String getFullName () {
		return fullName;
	}

	public void setFullName (String fullName) {
		this.fullName = fullName;
	}

	public Boolean getActive () {
		return active;
	}

	public void setActive (Boolean active) {
		this.active = active;
	}

	public String getRoles () {
		return roles;
	}

	public void setRoles (String roles) {
		this.roles = roles;
	}
}
