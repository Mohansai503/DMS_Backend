package com.dms.dmsproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.RegistrationDao;
import com.dms.dmsproject.model.UserRegistration;

@Service

public class RegstrationServiceimpl implements RegistrationService{
	
	@Autowired
	public RegistrationDao regstrationdao;
	
	@Override
	public UserRegistration save(UserRegistration userregistration) {
		regstrationdao.save(userregistration);
		return userregistration;
		
	}
	
	@Override
	public void markUserAsVerified(String email) {

	    UserRegistration user =
	            regstrationdao.findByUserEmailId(email);

	    if (user != null) {
	        user.setVerified(true);
	        user.setUserOtp(null);
	        regstrationdao.save(user);
	    }
	}

	@Override
	public List<UserRegistration> list() {
		List<UserRegistration> list=regstrationdao.findAll();
		return list;
	}

	@Override
	public void delete(int userid) {
		regstrationdao.deleteById(userid);
		
	}

	@Override
	public void edit(UserRegistration ureg) {
		regstrationdao.save(ureg);
		
	}

	   public boolean existsByEmail(String userEmailId) {
		   
		return regstrationdao.existsByUserEmailId(userEmailId);
	   }
}
