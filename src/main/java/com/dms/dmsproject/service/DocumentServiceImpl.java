package com.dms.dmsproject.service;


	import java.io.File;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.dao.DocumentDAO;
import com.dms.dmsproject.model.UploadResponse;

	@Service
	public class DocumentServiceImpl implements DocumentServices{

	

		    @Autowired
		    private DocumentDAO documentdao;

		    private final String UPLOAD_FOLDER = "C:/uploads/";

		    public UploadResponse saveDocument(MultipartFile file, String documentType) {

		        try {
		            // create upload folder if not exists
		            File directory = new File(UPLOAD_FOLDER);
		            if (!directory.exists()) {
		                directory.mkdirs();
		            }

		            String filePath = UPLOAD_FOLDER + file.getOriginalFilename();
		            file.transferTo(new File(filePath));

		            UploadResponse document = new UploadResponse();

		            document.setDocName(file.getOriginalFilename());
		            document.setDocType(documentType);
		            document.setSize(file.getSize() + " bytes");
		            document.setDocUploadType("File Upload");
		            document.setFilePath(filePath);

		            return documentdao.save(document);

		        } catch (Exception e) {
		            throw new RuntimeException("File upload failed", e);
		        }
		    }
		

	    @Override
	    public UploadResponse updateDocument(Integer id, MultipartFile file, String documentType) {

	        UploadResponse existing = documentdao.findById(id)
	                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

	        try {
	            // if new file coming - replace it
	            if (file != null && !file.isEmpty()) {

	                File directory = new File(UPLOAD_FOLDER);
	                if (!directory.exists()) directory.mkdirs();

	                String filePath = UPLOAD_FOLDER + file.getOriginalFilename();
	                file.transferTo(new File(filePath));

	                existing.setDocName(file.getOriginalFilename());
	                existing.setSize(file.getSize()+" bytes");
	                existing.setFilePath(filePath);
	            }

	            // change documentType if coming
	            if (documentType != null) {
	                existing.setDocType(documentType);
	            }

	            return documentdao.save(existing);

	        } catch (Exception e) {
	            throw new RuntimeException("Document update failed", e);
	        }
	    }
	}



