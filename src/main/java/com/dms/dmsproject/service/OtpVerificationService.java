package com.dms.dmsproject.service;

public interface OtpVerificationService {

	public boolean verifyOtp(String email, String enteredOtp);
	
}
