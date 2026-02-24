package com.dms.dmsproject.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.DocumentDAO;
import com.dms.dmsproject.model.UploadResponse;

@Service
public class DocDownloadServiceImpl implements DocDownloadService {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public ResponseEntity<Resource> downloadDocument(Integer docId) {

        // Fetch document from DB
        Optional<UploadResponse> optionalDoc = documentDAO.findById(docId);

        if (optionalDoc.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UploadResponse document = optionalDoc.get();

        // Validate file path
        String filePath = document.getFilePath();
        if (filePath == null || filePath.isBlank()) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(filePath);

        // Check file existence
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);

        // Detect content type
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(file.toPath());
        } catch (Exception e) {
            System.out.println(e);
        }

        // Return downloadable response
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + document.getDocName() + "\"").contentLength(file.length()).body(resource);
    }
}
