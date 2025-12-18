package com.dms.dmsproject.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dms.dmsproject.model.UploadResponse;

public interface DocumentDAO extends JpaRepository<UploadResponse, Integer> {
	 @Query("SELECT d FROM UploadResponse d WHERE " +
	           "LOWER(d.docName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(d.documentType) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    List<UploadResponse> searchDocuments(String keyword);

    // ✅ CORRECT QUERY METHOD
    List<UploadResponse> findByDocumentUser_Userid(int userId);

}