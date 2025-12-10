package com.dms.dmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dms.dmsproject.model.UserRegistration;

public interface RegistrationDao extends JpaRepository<UserRegistration,Integer>{

}
