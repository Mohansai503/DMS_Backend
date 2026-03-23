package com.dms.dmsproject.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dms.dmsproject.dto.OtpVerifyRequest;
import com.dms.dmsproject.model.UserRegistration;
import com.dms.dmsproject.security.JwtUtil;
import com.dms.dmsproject.service.LoginServices;
import com.dms.dmsproject.service.OtpService;
import com.dms.dmsproject.service.OtpVerificationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loginpage")
public class LoginController {

	@Autowired
	private LoginServices loginservices;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private OtpService otpService;

	@Autowired
	private OtpVerificationService otpVerificationService;

	/*
	 * @PostMapping("/login") public ResponseEntity<?> loginSave(@RequestBody
	 * UserRegistration usreg) {
	 * 
	 * UserRegistration existingUser =
	 * loginservices.findByEmail(usreg.getUserEmailId());
	 * 
	 * if (existingUser == null) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST)
	 * .body("Email not registered! Please register first."); }
	 * 
	 * String token = jwtUtil.generateToken(existingUser.getUserEmailId());
	 * 
	 * Map<String, String> response = new HashMap<>(); response.put("status",
	 * "success"); response.put("token", token);
	 * 
	 * return ResponseEntity.ok(response);
	 * 
	 * 
	 * }
	 */

	@PostMapping("/send-otp")
	public ResponseEntity<?> sendLoginOtp(@RequestBody UserRegistration user) {

		String message = otpService.generateOtpForLogin(user.getUserEmailId());

		return ResponseEntity.ok(message);
	}

	@PostMapping("/verify-otp")
    public ResponseEntity<?> verifyLoginOtp(
            @RequestBody OtpVerifyRequest request) {

    	UserRegistration user = otpVerificationService.verifyOtp(
                request.getEmail(),
                request.getOtp());
    	boolean isValid = true;
    	   if (user == null) {
    		   isValid = false;
   	    }
   
   	    if (user.getUserOtp() == null) {
   	     isValid = false;
   	    }
   
   	    // Check expiry
   	    if (user.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
   	     isValid = false;
   	    }
   
   	    if (! request.getOtp().equals(user.getUserOtp())) {
   	     isValid = false;
   	    }

        if (!isValid) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "failed");
            errorResponse.put("message", "Invalid or Expired OTP");

            return ResponseEntity.badRequest().body(errorResponse);
        }

        // OTP correct → generate JWT
        String token = jwtUtil.generateToken(request.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("token", token);
        response.put("userId", String.valueOf(user.getUserId()) );

        return ResponseEntity.ok(response);
    }
}
