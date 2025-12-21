package com.dms.dmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsproject.model.UserRegistration;

import com.dms.dmsproject.service.LoginServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loginpage")
public class LoginController {
	@Autowired
	public LoginServices loginservices;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginSave(@RequestBody UserRegistration usreg) {
		
		 UserRegistration existingUser = loginservices.findByEmail(usreg.getUserEmailId());
		 
		 if (existingUser == null) {
		        return ResponseEntity
		                .status(HttpStatus.BAD_REQUEST)
		                .body("Email not registered! Please register first.");
		    }
		 UserRegistration reg = loginservices.save(usreg);
		 
		 
		 return ResponseEntity.ok("Sucessfully login");
		 
		 
		
		 
	}		

}
