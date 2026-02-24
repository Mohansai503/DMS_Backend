package com.dms.dmsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsproject.service.DocDownloadService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DownloadDocumentController {

    @Autowired
    private DocDownloadService docDownloadService;

    @GetMapping("/download/{docId}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Integer docId) {
        return docDownloadService.downloadDocument(docId);
    }
}
