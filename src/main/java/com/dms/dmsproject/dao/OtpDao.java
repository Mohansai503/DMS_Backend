
	package com.dms.dmsproject.dao;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;


	import com.dms.dmsproject.model.UserRegistration;

	@Repository
	public interface OtpDao extends JpaRepository<UserRegistration, Integer> {

		UserRegistration findByUserEmailId(String userEmailId);
	}
