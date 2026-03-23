package com.dms.dmsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsproject.model.UserRegistration;
//import com.dms.dmsproject.helper.OtpGeneration;
import com.dms.dmsproject.service.OtpVerificationServiceImpl;

@RestController
@RequestMapping("/otpapi")
@CrossOrigin(origins = "*")
public class OtpController {
	
	//@Autowired
	//private OtpGeneration otpGen;
	
	@Autowired
	private OtpVerificationServiceImpl otpVerifyServ;
	
	/*
	//Registration end point
	@GetMapping("/generate")
	public ResponseEntity<String> handlingOtpReg(@RequestParam String email, @RequestParam String userName) {
	    if (otpGen.isUserRegistered(email)) {
	        return ResponseEntity.badRequest().body("Email already registered. Please login.");
	    }

	    String otp = otpGen.generateAndSaveOtp(email, userName);
	    return ResponseEntity.ok(otp);
	}
	
	//Login end point
		@GetMapping("/genlogotp")
	    public ResponseEntity<String> handlingOtplog(@RequestParam String email) {

	        if (otpGen.isUserNotRegistered(email)) {
	            return ResponseEntity.badRequest().body("Email is not registered. Please Register.");
	        }

	        String otp = otpGen.generateAndSaveOtp(email);
	        return ResponseEntity.ok(otp);
	    }
	    
	    */
		
	// Common
	@PostMapping("/verify")
	public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {

        UserRegistration isValid = otpVerifyServ.verifyOtp(email, otp);

        if (isValid == null) {
            return ResponseEntity.badRequest().body("Invalid or Expired OTP");
        }

        return ResponseEntity.ok("OTP Verified Successfully");
    }
	
	
}
