package com.dms.dmsproject.helper;

import java.security.SecureRandom;

public class OtpGeneration {
	
	public static String generateOtp(int length) {
		SecureRandom random=new SecureRandom();
		StringBuilder otp=new StringBuilder();
		
		for(int i=0;i<length;i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}
	
	
	private static final String charecters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	public static String generateOtp1(int length) {
		SecureRandom random=new SecureRandom();
		StringBuilder otp1=new StringBuilder();
		
		for(int i=0;i<length;i++) {
			int index=random.nextInt(charecters.length());
			otp1.append(charecters.charAt(index));
		}
		return otp1.toString();
		
	}
	
	public static void main(String[] args) {
		String otp=generateOtp(6);
		String otp1=generateOtp1(6);
		System.out.println("Your OTP is:"+otp);
		System.out.println("Your OTP is:"+otp1);
	}

}
