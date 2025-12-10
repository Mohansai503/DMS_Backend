package com.dms.dmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dms.dmsproject.model.DocumentUser;

@Repository
public interface OtpDao extends JpaRepository<DocumentUser, Integer> {

	DocumentUser findByUserEmailId(String userEmailId);
}
