package com.dms.dmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dms.dmsproject.model.UserRegistration;

@Repository
public interface RegistrationDao
        extends JpaRepository<UserRegistration,Integer>{

    boolean existsByUserEmailId(String userEmailId);

    UserRegistration findByUserEmailId(String userEmailId);
}


