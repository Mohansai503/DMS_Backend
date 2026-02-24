package com.dms.dmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsproject.dto.ShareDocumentDTO;
import com.dms.dmsproject.service.ShareDocumentService;

@RestController
@RequestMapping("/shareapi")
public class ShareDocumentController {
	 @Autowired
	    private ShareDocumentService shareDocumentService;

	    // Get share details using shareId
	    @GetMapping("/share/{shareId}")
	    public ResponseEntity<ShareDocumentDTO> getShareDetails(@PathVariable Integer shareId) {

	        ShareDocumentDTO share = shareDocumentService.getShareDetails(shareId);

	        return ResponseEntity.ok(share);
	    }
	    
	    // Get received documents using userId
	    @GetMapping("/received/{userId}")
	    public ResponseEntity<List<ShareDocumentDTO>> 
	            getReceivedDocuments(@PathVariable Integer userId) {

	        return ResponseEntity.ok(
	                shareDocumentService.getReceivedDocuments(userId)
	        );
	    }
}
