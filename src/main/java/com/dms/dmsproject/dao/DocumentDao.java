
	package com.dms.dmsproject.dao;

	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	import com.dms.dmsproject.model.Document;

	@Repository
	public interface DocumentDao extends JpaRepository<Document, Integer>{
		List<Document> findByUserId(int userId);

	}
