package com.dms.dmsproject.service;

import java.util.List;

import com.dms.dmsproject.model.UserRegistration;
public interface LoginServices {
	
	public UserRegistration save(UserRegistration usreg);
	public UserRegistration findByEmail(String userEmailId);

}
