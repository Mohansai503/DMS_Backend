package com.dms.dmsproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="document_user")

public class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_user_id")
	int userId;
	@Column(name="document_user_name")
	String userName;
	@Column(name="document_user_emailid")
	String userEmailId;
	@Column(name="document_user_otp")
	String userOtp;
	
	@Column(name = "otp_expiry_time")
	private LocalDateTime otpExpiryTime;

	@Column(name = "is_verified")
	private Boolean isVerified;
	
	public LocalDateTime getOtpExpiryTime() {
		return otpExpiryTime;
	}
	public void setOtpExpiryTime(LocalDateTime otpExpiryTime) {
		this.otpExpiryTime = otpExpiryTime;
	}
	public Boolean isVerified() {
		return isVerified;
	}
	public void setVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	public String getUserOtp() {
		return userOtp;
	}
	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
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
