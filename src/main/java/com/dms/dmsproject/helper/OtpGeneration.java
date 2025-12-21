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
		
		public String generateAndSaveOtp(String userEmailId,String userName ) {
			String userOtp=generateOtp(6);
		 
			UserRegistration userReg=otpDao.findByUserEmailId(userEmailId);
			if(userReg==null) {
				userReg=new UserRegistration();
				userReg.setUserEmailId(userEmailId);
				userReg.setUserName(userName);
					
			}
			userReg.setUserOtp(userOtp);
		
		otpDao.save(userReg);
		return userOtp;
		
		
		}
}
		
		
		
		

