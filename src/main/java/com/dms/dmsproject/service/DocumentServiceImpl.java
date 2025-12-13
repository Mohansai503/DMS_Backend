package com.dms.dmsproject.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.dao.DocumentDAO;
import com.dms.dmsproject.model.UploadResponse;

@Service
public class DocumentServiceImpl implements DocumentServices {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private DocumentDAO documentdao;

    // prefer configured value; empty means "not set"
    @Value("${file.upload-dir:}")
    private String UPLOAD_FOLDER;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        try {
            // If no property provided, create/use a folder named "dms-uploads" inside project working dir
            if (UPLOAD_FOLDER == null || UPLOAD_FOLDER.isBlank()) {
                String userDir = System.getProperty("user.dir"); // project's working directory
                UPLOAD_FOLDER = Paths.get(userDir, "dms-uploads").toString();
            }
            uploadPath = Paths.get(UPLOAD_FOLDER).toAbsolutePath().normalize();

            // create directories on startup so permissions problem appears early
            Files.createDirectories(uploadPath);

            logger.info("Upload folder resolved to: {}", uploadPath.toString());
        } catch (IOException e) {
            logger.error("Failed to create upload folder '{}'", UPLOAD_FOLDER, e);
            throw new RuntimeException("Failed to initialize upload folder", e);
        }
    }

    @Override
    public UploadResponse saveDocument(MultipartFile file, String documentType) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("No file provided");
            }

            // ensure directories exist (again, safe)
            Files.createDirectories(uploadPath);

            // sanitize filename (avoid path traversal)
            String originalFileName = Path.of(file.getOriginalFilename()).getFileName().toString();
            Path target = uploadPath.resolve(originalFileName);

            // copy file stream -> target (replace if exists)
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            UploadResponse document = new UploadResponse();
            document.setDocName(originalFileName);
            document.setDocType(documentType);
            document.setSize(file.getSize() + " bytes");
            document.setDocUploadDate(LocalDate.now().toString());
            document.setFilePath(target.toString());

            return documentdao.save(document);

        } catch (IOException e) {
            logger.error("File save failed", e);
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during upload", e);
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }
    }

    @Override
    public UploadResponse updateDocument(Integer id, MultipartFile file, String documentType) {

        UploadResponse existing = documentdao.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

        try {
            // if new file coming - replace it
            if (file != null && !file.isEmpty()) {
                Files.createDirectories(uploadPath);

                String originalFileName = Path.of(file.getOriginalFilename()).getFileName().toString();
                Path target = uploadPath.resolve(originalFileName);

                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

                existing.setDocName(originalFileName);
                existing.setSize(file.getSize() + " bytes");
                existing.setFilePath(target.toString());
            }

            // change documentType if provided
            if (documentType != null) {
                existing.setDocType(documentType);
            }

            return documentdao.save(existing);

        } catch (IOException e) {
            logger.error("File update failed", e);
            throw new RuntimeException("Document update failed: " + e.getMessage(), e);
        }
    }
}
