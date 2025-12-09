
	
	package com.dms.dmsproject.service;

	import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.model.UploadResponse;

	public interface DocumentServices {

		UploadResponse saveDocument(MultipartFile file,String documentType);

		UploadResponse updateDocument(Integer id, MultipartFile file, String documentType);
	}


