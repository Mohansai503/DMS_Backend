package com.dms.dmsproject.service;

import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dms.dmsproject.dao.DocumentDAO;
import com.dms.dmsproject.dao.RegistrationDao;
import com.dms.dmsproject.model.UploadResponse;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class DocumentServiceImpl implements DocumentServices {

    @Autowired
    private DocumentDAO documentdao;
    
    @Autowired
    private RegistrationDao registrationDao;
    
    @Value("${file.upload-dir}")
    private String UPLOAD_FOLDER;
    
    public UploadResponse saveDocument(MultipartFile file, String documentType,Integer userId) {
    	
    	UserRegistration user = registrationDao.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found..."));

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
            document.setDocumentType(documentType);
            document.setDocSize(file.getSize() + " bytes");
            document.setDocUploadDate(LocalDateTime.now());

            document.setDocumentUser(user);
            //document.setUploadDate(LocalDate.now().toString());

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
            existing.setDocSize(file.getSize()+" bytes");
            existing.setFilePath(filePath);
        }

        // change documentType if coming
        if (documentType != null) {
            existing.setDocumentType(documentType);
        }

        return documentdao.save(existing);

    } catch (Exception e) {
        throw new RuntimeException("Document update failed", e);
    }
}

/*@Override
public List<UploadResponse> searchDocuments(String keyword) {
    return documentdao.searchDocuments(keyword);
}*/

    @Transactional
    public String deleteDm(int docId) {
        UploadResponse doc = documentdao.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        
        //First delete → soft delete
        if (doc.getDeleted() == 0) {
            doc.setDeleted(1);
            documentdao.save(doc);
            return "Document moved to Trash";
        }
        //Second delete → permanent delete

        File file = new File(doc.getFilePath());
        if (file.exists()) { 
        	file.delete();
        }

        documentdao.delete(doc);
        return "Document permanently deleted";
    }
    
    @Transactional
    public String restoreDocument(int docId) {

        UploadResponse doc = documentdao.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        if (doc.getDeleted() == 0) {
            return "Document is already active";
        }

        doc.setDeleted(0);
        documentdao.save(doc);
        return "Document restored successfully";
    }


   // @Transactional(readOnly = true)
   // public List<UploadResponse> list(int userId) {
        //return documentdao.findByDocumentUser_Userid(userId);
   // }




    @Transactional(readOnly = true)
	public List<UploadResponse> getDocumentsByType(int userId, String type) {
		if("home".equalsIgnoreCase(type)) {
			return documentdao.findAllByUserId(userId);
		}
		
		if("recent".equalsIgnoreCase(type)) {
			LocalDateTime last7Days = LocalDateTime.now().minusDays(7);
			return documentdao.findRecentDocuments(userId, last7Days);
		}
		
		if("trash".equalsIgnoreCase(type)) {
			return documentdao.findDeletedDocuments(userId);
		}
		throw new IllegalArgumentException("Invalid type: " + type);
	
	}
}
