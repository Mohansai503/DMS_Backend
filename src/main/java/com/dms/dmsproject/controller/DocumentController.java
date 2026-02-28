

package com.dms.dmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.dto.DocumentDto;
import com.dms.dmsproject.model.UploadResponse;
import com.dms.dmsproject.model.UserRegistration;
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
	                @RequestParam("documentType") String documentType,
	                @RequestParam("userId") Integer userId
	        ) {
	        	
	        	// ✅ THIS IS THE EXACT PLACE
	            UploadResponse response =documentService.saveDocument(file, documentType, userId);
	            return ResponseEntity.ok(response );
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
	    
	    
	    
	    @GetMapping("/delete/{docId}")
		//Full control over status, headers, and body
		public ResponseEntity<String> deleteDocument(@PathVariable int docId) {
	    	UploadResponse dm=new UploadResponse();
			dm.setDocId(docId);
			String message=documentService.deleteDm(docId);
			return ResponseEntity.ok(message);		
		}
		
	    
	    @PostMapping("/list/{userId}")
	    public ResponseEntity<List<UploadResponse>> getDocumentsByType(
	            @PathVariable int userId,
	            @RequestBody DocumentDto docdto) {

	        List<UploadResponse> message= documentService.getDocumentsByType(userId, docdto.getType());
	        return ResponseEntity.ok(message);
	    }
	    
	    
	    @PostMapping("/restore/{docId}")
		public ResponseEntity<String> restoreDocument(@PathVariable int docId) {
		    documentService.restoreDocument(docId);
		    return ResponseEntity.ok("Document Restored Successfully" + docId);
		}
	    
	    
	    //@GetMapping("/{userId}")
	    //public List<UploadResponse> getAllDocumentsByUserId(@PathVariable int userId) {
	        //return documentService.list(userId);
	    //}
	    
	    @GetMapping("/trash/{userId}")
	    public ResponseEntity<List<UploadResponse>> getTrashDocuments(
	            @PathVariable int userId) {

	        List<UploadResponse> trashDocs =
	                documentService.getDocumentsByType(userId, "trash");

	        return ResponseEntity.ok(trashDocs);
	    }


}