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
	    private String docType;
	    
	@Column(name="document_name")
	    private String docName;
	    
	@Column(name="document_size")
	    private String size;
	
	@Column(name="document_upload_at")
	    private String docUploadType;
	
	@Column(name="file_path")
	    private String filePath;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public String getDocUploadType() {
		return docUploadType;
	}

	public void setDocUploadType(String docUploadType) {
		this.docUploadType = docUploadType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	   


}
