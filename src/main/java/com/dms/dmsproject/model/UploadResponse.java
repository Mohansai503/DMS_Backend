package com.dms.dmsproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="document")
public class UploadResponse {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="document_id")
	    private Integer docId;
	
	@Column(name="document_type")
	    private String documentType;
	    
	@Column(name="document_name")
	    private String docName;
	    
	@Column(name="document_size")
	    private String size;
	
	@Column(name="document_upload_at")
	    private String docUploadDate;
	
	@Column(name="file_path")
	    private String filePath;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocType() {
		return documentType;
	}

	public void setDocType(String docType) {
		this.documentType = docType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDocUploadDateS() {
		return docUploadDate;
	}

	public void setDocUploadDate(String docUploadDate) {
		this.docUploadDate = docUploadDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	   


}
