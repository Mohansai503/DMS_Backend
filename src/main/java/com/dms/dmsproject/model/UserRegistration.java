package com.dms.dmsproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="document_user")

public class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_user_id")
	int userId;
	@Column(name="document_user_name")
	String userName;
	@Column(name="document_user_emailid")
	String userEmailId;
	@Column(name="document_user_otp")
	String userOtp;
	
	@OneToMany(mappedBy = "documentUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UploadResponse> documents;
	
	public List<UploadResponse> getDocuments() {
		return documents;
	}
	public void setDocuments(List<UploadResponse> documents) {
		this.documents = documents;
	}
	public String getUserOtp() {
		return userOtp;
	}
	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

}
