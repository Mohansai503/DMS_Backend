
package com.dms.dmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.dms.dmsproject.model.Document;
	import com.dms.dmsproject.service.DocumentServices;

	@RestController
	@RequestMapping("/document")
	public class DocumentConroller {
		
		@Autowired
		private DocumentServices documentServices;
		
		@GetMapping("/delete/{documentId}")
		//Full control over status, headers, and body
		public ResponseEntity<String> deleteDocument(@PathVariable int documentId) {
			Document dm=new Document();
			dm.setDocumentId(documentId);
			documentServices.deleteDm(documentId);
			return ResponseEntity.ok("Document deleted successfully with ID:" + documentId);		
		}
		
		@GetMapping("/{userId}")
		public List<Document>getAllDocumentsByUserId(@PathVariable int userId){
			return documentServices.list(userId);
		 
		}

	}
