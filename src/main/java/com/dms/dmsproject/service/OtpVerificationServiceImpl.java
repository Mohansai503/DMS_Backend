package com.dms.dmsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.OtpDao;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

	@Autowired
	private OtpDao otpDao;
	
	public boolean verifyOtp(String email, String enteredOtp) {
		
		UserRegistration userReg = otpDao.findByUserEmailId(email);
		
//		if(user == null && user.getUserOtp() == null) {
//			return false;
//		}
		
		if (userReg == null) {
	        return false;
	    }

	    if (userReg.getUserOtp() == null) {
	        return false;
	    }

	    boolean isValid = enteredOtp.equals(userReg.getUserOtp());

	    if (isValid) {
	        userReg.setUserOtp(null); // invalidate OTP
	        otpDao.save(userReg);
	    }
		return isValid;
	}

}
