package com.dms.dmsproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.dmsproject.model.ShareDocument;

public interface ShareDocumentDAO extends JpaRepository<ShareDocument, Integer> {

	//List<ShareDocument> findByShereId(Integer shareId);
	List<ShareDocument> findBySharedToUserId(Integer userId);
}
