package com.dms.dmsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.OtpDao;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

	@Autowired
	private OtpDao otpDao;
	
	@Override
	public UserRegistration verifyOtp(String email, String enteredOtp) {

	    UserRegistration user = otpDao.findByUserEmailId(email);

//	 

	    return user;
	}

}
