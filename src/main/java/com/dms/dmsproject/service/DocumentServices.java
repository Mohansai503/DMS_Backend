package com.dms.dmsproject.service;

	import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.model.UploadResponse;
import com.dms.dmsproject.model.UserRegistration;

	public interface DocumentServices {

		UploadResponse saveDocument(MultipartFile file,String documentType,UserRegistration documentUser);

		UploadResponse updateDocument(Integer id, MultipartFile file, String documentType);
		
		void deleteDm(int docId);
		
		List<UploadResponse> list(int userId);
		
		
	
	}