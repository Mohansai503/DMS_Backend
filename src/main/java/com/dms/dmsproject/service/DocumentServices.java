package com.dms.dmsproject.service;

	import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.model.UploadResponse;
import com.dms.dmsproject.model.UserRegistration;

	public interface DocumentServices {

		UploadResponse saveDocument(MultipartFile file,String documentType,UserRegistration documentUser);

		UploadResponse updateDocument(Integer id, MultipartFile file, String documentType);
		
		String deleteDm(int docId);
		
		String restoreDocument(int docId);
		public List<UploadResponse> searchDocuments(String docName,String docType, String uploadedDate);
		
		//List<UploadResponse> list(int userId);

		List<UploadResponse> getDocumentsByType(int userId, String type);

		byte[] getDocumentFile(int docId);

		UploadResponse getById(int docId);
		
		
	
	}