
package com.dms.dmsproject.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.dmsproject.dao.DocumentDao;
import com.dms.dmsproject.model.Document;

	@Service
	public class DocumentServiceImpl implements DocumentServices {
		
		@Autowired
		DocumentDao documentDao;

		@Transactional
		public void deleteDm(int documentId) {
			//Load the document from the database
			//It looks for a Document by its ID.
            //If the document doesn’t exist, it throws a RuntimeException.
			Document doc = documentDao.findById(documentId)
	                .orElseThrow(() -> new RuntimeException("Document not found"));
			// Delete file from the file system
			File file = new File(doc.getFilePath());
			if (file.exists()) {
	            if (!file.delete()) {
	                throw new RuntimeException("Failed to delete file: " + doc.getFilePath());
	            }
	        }
			documentDao.delete(doc);
			
		}

		@Transactional(readOnly = true)
		public List<Document> list(int userId) {
		return documentDao.findByUserId(userId);
		}


	}
