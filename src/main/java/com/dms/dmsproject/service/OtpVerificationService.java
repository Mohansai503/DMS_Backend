package com.dms.dmsproject.service;

import com.dms.dmsproject.model.UserRegistration;

public interface OtpVerificationService {

	public UserRegistration verifyOtp(String email, String enteredOtp);
	
}


