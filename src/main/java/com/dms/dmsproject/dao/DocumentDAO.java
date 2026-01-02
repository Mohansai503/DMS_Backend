package com.dms.dmsproject.dao;
import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;


import com.dms.dmsproject.model.UploadResponse;


@Repository
public interface DocumentDAO extends JpaRepository<UploadResponse, Integer> {
	
	
	@Query("""
		    SELECT d FROM UploadResponse d
		    WHERE (:docName IS NULL OR LOWER(d.docName) LIKE LOWER(CONCAT('%', :docName, '%')))
		      AND (:docType IS NULL OR LOWER(d.documentType) LIKE LOWER(CONCAT('%', :docType, '%')))
		      AND (:start IS NULL OR d.docUploadDate BETWEEN :start AND :end)""")
		
		List<UploadResponse> searchDocuments(
		        @Param("docName") String docName,
		        @Param("docType") String docType,
		        @Param("start") LocalDateTime start,
		        @Param("end") LocalDateTime end
		);

	// @Query("SELECT d FROM UploadResponse d WHERE " +
	           //"LOWER(d.docName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           //"LOWER(d.documentType) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    //List<UploadResponse> searchDocuments(String keyword);

    // ✅ CORRECT QUERY METHOD
    //List<UploadResponse> findByDocumentUser_Userid(int userId);
	 
	 @Query("SELECT d FROM UploadResponse d WHERE d.documentUser.userId = :userId")
	 List<UploadResponse> findAllByUserId(int userId);
	 
	 @Query("SELECT d FROM UploadResponse d WHERE d.documentUser.userId = :userId AND d.docUploadDate >= :date")
	    List<UploadResponse> findRecentDocuments(
	            @Param("userId") int userId,
	            @Param("date") LocalDateTime date);

	 @Query("SELECT d FROM UploadResponse d WHERE d.documentUser.userId = :userId AND d.deleted = 1")
	 List<UploadResponse> findDeletedDocuments(@Param("userId") int userId);
	 
	 
}