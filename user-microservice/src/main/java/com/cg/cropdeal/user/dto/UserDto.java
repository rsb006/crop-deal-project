package com.cg.cropdeal.user.dto;

import com.cg.cropdeal.user.model.Address;
import com.cg.cropdeal.user.model.Bank;
import com.cg.cropdeal.user.model.User;

public class UserDto {
	
	
	  private Long userId ;
   
	  private String userFullName ;
	
      private String userType ;
	
	  private String userName ;
	  
	  private String password ; 
	  
	  private long mobileNo;
	  
	  private String emailId;
	
	  private  String userStatus ;

	  BankDto bank;

	  AddressDto address;

	public UserDto(Long userId, String userFullName, String userType, String userName, String password, long mobileNo,
			String emailId, String userStatus,  BankDto bank, AddressDto address) {
		super();
		this.userId = userId;
		this.userFullName = userFullName;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.userStatus = userStatus;
		this.bank = bank;
		this.address = address;
	}

	public UserDto() {
		
	}

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

	public BankDto getBank() {
		return bank;
	}

	public void setBank(BankDto bank) {
		this.bank = bank;
	}

	public  AddressDto  getAddress() {
		return address;
	}

	public void setAddress( AddressDto  address) {
		this.address = address;
	}

	
	 public User getUserFromUserDto(User userdto) {
		 var user=new User();
		 user.setAddress(userdto.getAddress());
		 user.setBank(userdto.getBank());
		 user.setEmailId(userdto.getEmailId());
		 user.setMobileNo(userdto.getMobileNo());
		 user.setPassword(userdto.getPassword());
		 user.setUserFullName(userdto.getUserFullName());
		 user.setUserId(userdto.getUserId());
		 user.setUserName(userdto.getUserName());
		 user.setUserStatus(userdto.getUserStatus());
		 user.setUserType(userdto.getUserType());;
		 return user;
		 
	 } 

}
