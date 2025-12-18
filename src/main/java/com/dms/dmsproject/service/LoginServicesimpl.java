package com.dms.dmsproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.LoginDao;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class LoginServicesimpl implements LoginServices{
	@Autowired
	public LoginDao logindao;

	@Override
	public UserRegistration save(UserRegistration usreg) {
		logindao.save(usreg);
		return null;
	}

	@Override
	public UserRegistration findByEmail(String userEmailId) {
		return logindao.findByUserEmailId(userEmailId);
	}



}
