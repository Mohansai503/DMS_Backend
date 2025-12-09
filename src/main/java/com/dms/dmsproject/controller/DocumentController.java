package com.dms.dmsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.model.UploadResponse;
import com.dms.dmsproject.service.DocumentServices;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
	@RequestMapping("/api/documents")
	public class DocumentController {

	    @Autowired
	    private DocumentServices documentService;

	   
	    @PostMapping(value = "/upload", consumes = "multipart/form-data")
	    public ResponseEntity<UploadResponse> uploadDocument(
	            @RequestParam("file") MultipartFile file,
	            @RequestParam("documentType") String documentType
	    ) {
	        UploadResponse response = documentService.saveDocument(file, documentType);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping(value = "/edit/{id}", consumes = "multipart/form-data")
	    public ResponseEntity<UploadResponse> editDocument(
	            @PathVariable Integer id,
	            @RequestParam(value = "file", required = false) MultipartFile file,
	            @RequestParam(value = "documentType", required = false) String documentType
	    ) {
	        UploadResponse updated = documentService.updateDocument(id, file, documentType);
	        return ResponseEntity.ok(updated);
	    }

}
