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
import com.dms.dmsproject.service.EmailServices;
import com.dms.dmsproject.service.RegistrationService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/registration")

public class RegistrationController {
	@Autowired
	private RegistrationService registrationservice;
	
//	@Autowired
//	public EmailServices emailservices;
	
	@PostMapping("/regsave")
	public ResponseEntity<?> saveUser(@RequestBody UserRegistration user) {
	
		
		   if (registrationservice.existsByEmail(user.getUserEmailId())) {
		        return ResponseEntity
		                .status(HttpStatus.BAD_REQUEST)
		                .body("Email already exists! Try another email.Please proceed with login");
		    }
		   UserRegistration reg = registrationservice.save(user);
		 

		   return ResponseEntity
	            .status(HttpStatus.OK)
	            .body("Registration done sucessfully Otp sent to your mail" +reg.getUserEmailId());
		

	}
	
	@GetMapping("/")
	public List<UserRegistration>list(){
		List<UserRegistration>list=registrationservice.list();
		return list;
		
	}
	@GetMapping("/delete/{userid}")
	public int deleteid(@PathVariable int userid) {
		registrationservice.delete(userid);
		return userid;
		
	}
	
	@PostMapping("/edit/{userid}")
	public int editid(@PathVariable int userid,@RequestBody UserRegistration ureg) {
		registrationservice.edit(ureg);
		return userid;
		
	}

}
