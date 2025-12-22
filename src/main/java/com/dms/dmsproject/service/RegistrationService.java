package com.dms.dmsproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dms.dmsproject.model.UserRegistration;

@Service
public interface RegistrationService {
	
	public UserRegistration save(UserRegistration user);

	public List<UserRegistration> list();

	public void delete(int userid);

	public void edit(UserRegistration ureg);

	public boolean existsByEmail(String userEmailId);

	//public UserRegistration findByEmail(String userEmailId);

}
