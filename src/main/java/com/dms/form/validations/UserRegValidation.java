package com.dms.form.validations;

import java.util.ArrayList;
import java.util.List;

public class UserRegValidation {

	public List<String> userRegValidation(String userName, String userMail, int otp) {
		
		List<String> list = new ArrayList<>();
		String otpStr = String.valueOf(otp);
		
		if (userName == null || userName.trim().isEmpty() || userName.length() < 3) {
		    list.add("Username must be at least 3 characters.");
		}
		
		if(userMail == null || userMail.trim().isEmpty() || !userMail.contains("@") || !userMail.contains("gmail.com")) {
			list.add("Enter a valid email");
		}
		
		if (otpStr == null || !otpStr.matches("\\d{6}")) {
		    list.add("OTP must be a valid 6-digit number.");
		}
		
		return list;
		
	}
	
}
