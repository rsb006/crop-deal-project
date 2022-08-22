package com.cg.cropdeal.authentication.model;

import javax.persistence.*;

@Entity
public class Account {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	int accountId;
	@Column (nullable = false, unique = true)
	String email;
	@Column (nullable = false)
	String password;
	@Column (nullable = false)
	String fullName;

	public Account () {

	}

	public Account (String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public int getAccountId () {
		return accountId;
	}

	public void setAccountId (int accountId) {
		this.accountId = accountId;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
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

	@Override
	public String toString () {
		return "Account{" + "accountId=" + accountId + ", username='" + email + '\'' + ", password='" + password + '\'' + ", fullName='" + fullName + '\'' + '}';
	}
}
