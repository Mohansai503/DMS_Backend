/*
package com.dms.dmsproject.helper;


import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.dmsproject.dao.OtpDao;
import com.dms.dmsproject.model.UserRegistration;

@Component
public class OtpGeneration {
	
	@Autowired
    private OtpDao otpDao;
	
		
		public static String generateOtp(int length) {
			SecureRandom random=new SecureRandom();
			StringBuilder userOtp=new StringBuilder();
			
			for(int i=0;i<length;i++) {
				userOtp.append(random.nextInt(10));
			}
			return userOtp.toString();
		}
		
		// Registration check
	    public boolean isUserRegistered(String email) {
	        return otpDao.findByUserEmailId(email) != null;
	    }

	    // Login check
	    public boolean isUserNotRegistered(String email) {
	        return otpDao.findByUserEmailId(email) == null;
	    }
	    
	  //Overload For Registration
	    public String generateAndSaveOtp(String email, String userName) {
	        String otp = generateOtp(6);

	        UserRegistration user = new UserRegistration();
	        user.setUserEmailId(email);
	        user.setUserName(userName);
	        user.setUserOtp(otp);

	        otpDao.save(user);
	        return otp;
	    }
		
		//Overload For Login
	    public String generateAndSaveOtp(String email) {
	        UserRegistration user = otpDao.findByUserEmailId(email);

	        if (user == null) {
	            throw new RuntimeException("User not found");
	        }

	        String otp = generateOtp(6);
	        user.setUserOtp(otp);
	        otpDao.save(user);

	        return otp;
	    }
}
*/
		
		
		
		

