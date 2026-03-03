package com.dms.dmsproject.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.OtpDao;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

	@Autowired
	private OtpDao otpDao;
	
	@Override
	public boolean verifyOtp(String email, String enteredOtp) {

	    UserRegistration user = otpDao.findByUserEmailId(email);

	    if (user == null) {
	        return false;
	    }

	    if (user.getUserOtp() == null) {
	        return false;
	    }

	    // Check expiry
	    if (user.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
	        return false;
	    }

	    if (!enteredOtp.equals(user.getUserOtp())) {
	        return false;
	    }

	    return true;
	}

}
