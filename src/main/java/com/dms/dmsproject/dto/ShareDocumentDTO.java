package com.dms.dmsproject.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_NULL) //While converting Java objects to JSON, it ignore null fields
public class ShareDocumentDTO {
	
	private LocalDateTime date;
	private UserRegistrationDTO sharedBy;
	private UserRegistrationDTO sharedTo;
	private Integer documentId;
	private String documentType;
    private String docName;
    private String docSize;
	
	public ShareDocumentDTO() {}
	
	public ShareDocumentDTO(Integer shareId, LocalDateTime date, UserRegistrationDTO sharedBy,UserRegistrationDTO sharedTo, 
			Integer documentId, String documentType, String docName, String docSize) {
		this.shareId = shareId;
		this.date = date;
		this.sharedBy = sharedBy;
		this.sharedTo = sharedTo;
		this.documentId = documentId;
		this.documentType= documentType;
		this.docName=docName; 
		this.docSize=docSize;
	}
	
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	private Integer shareId;
	public Integer getShareId() {
		return shareId;
	}
	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public UserRegistrationDTO getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(UserRegistrationDTO sharedBy) {
		this.sharedBy = sharedBy;
	}
	public UserRegistrationDTO getSharedTo() {
		return sharedTo;
	}
	public void setSharedTo(UserRegistrationDTO sharedTo) {
		this.sharedTo = sharedTo;
	}
	public Integer getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	
	
}
