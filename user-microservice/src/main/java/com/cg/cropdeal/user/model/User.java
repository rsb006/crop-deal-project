package com.cg.cropdeal.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
      private Long userId ;
	
	  @Column(nullable=false)
      private String userFullName ;
	
	  @Column(nullable=false)
      private String userType ;
	
	  private String userName ;
	  
	  private String password ; 
	  
	  @Column(nullable=false)
	  private long mobileNo;
	  
	  @Column(nullable=false)
	  private String emailId;
	
	  @Column(nullable=true)
	  private  String userStatus ;
	
 
	
	   @OneToOne(cascade =CascadeType.ALL)
	  //@JoinColumn(name="account_no")
	   Bank bank;
	
	   @OneToOne(cascade =CascadeType.ALL)
	  //@JoinColumn(name="address_id")
	   Address address;

	    public Long getUserId() {
		    return userId;
	    }

	    public void setUserId(Long userId) {
	    	this.userId = userId;
	    }

	    public String getUserFullName() {
	    	return userFullName;
	    }

	    public void setUserFullName(String userFullName) {
	    	this.userFullName = userFullName;
	    }

	    public String getUserType() {
	    	return userType;
	    }

	    public void setUserType(String userType) {
	    	this.userType = userType;
	    }

	    public String getUserName() {
	    	return userName;
	    }

	    public void setUserName(String userName) {
	    	this.userName = userName;
	    }

	    public String getPassword() {
	    	return password;
	    }

	    public void setPassword(String password) {
	    	this.password = password;
	    }

	    public long getMobileNo() {
	    	return mobileNo;
	    }

	    public void setMobileNo(long mobileNo) {
	    	this.mobileNo = mobileNo;
	    }

	    public String getEmailId() {
	    	return emailId;
	    }

	    public void setEmailId(String emailId) {
	    	this.emailId = emailId;
	    }

	    public String getUserStatus() {
	    	return userStatus;
	    }

	    public void setUserStatus(String userStatus) {
	    	this.userStatus = userStatus;
	    }

	    public Bank getBank() {
	    	return bank;
	    }

	    public void setBank(Bank bank) {
	    	this.bank = bank;
	    }

	    public Address getAddress() {
	    	return address;
	    }

	    public void setAddress(Address address) {
	    	this.address = address;
	    }
	
	

	}
