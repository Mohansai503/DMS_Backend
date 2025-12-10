
	package com.dms.dmsproject.model;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;

	@Entity
	@Table(name="document")
	public class Document {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="document_id")
		int documentId;
		
		@Column(name="document_name")
		String documentName;
		
		@Column(name="file_path")
        String filePath;
		
		@Column(name="document_user_id")
		int userId;
		
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public int getDocumentId() {
			return documentId;
		}
		public void setDocumentId(int documentId) {
			this.documentId = documentId;
		}
		public String getDocumentName() {
			return documentName;
		}
		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}	
		
	}
