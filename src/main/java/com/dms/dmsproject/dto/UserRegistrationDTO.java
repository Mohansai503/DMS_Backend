package com.dms.dmsproject.dto;

public class UserRegistrationDTO {

	private int userId;
	private String userName;
	private String userEmailId;
	
	public UserRegistrationDTO() {
		
	}
	
	public UserRegistrationDTO(int userId, String userName, String userEmailId) {
		this.userId = userId;
		this.userName = userName;
		this.userEmailId = userEmailId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	
}
