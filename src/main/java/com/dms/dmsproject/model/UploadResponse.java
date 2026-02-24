package com.dms.dmsproject.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "document")
public class UploadResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Integer docId;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_name")
    private String docName;
    

	@Column(name = "document_size")
    private String docSize;

    @Column(name = "document_upload_at")
    private LocalDateTime docUploadDate;

    @Column(name = "file_path")
    private String filePath;

    // ✅ RELATIONSHIP
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_user_id")
    @JsonBackReference
    private UserRegistration documentUser;
    
    @Column(name = "document_delete", columnDefinition = "boolean default 0")
    private int deleted;
    

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}
   
    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
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

	

	public LocalDateTime getDocUploadDate() {
		return docUploadDate;
	}

	public void setDocUploadDate(LocalDateTime docUploadDate) {
		this.docUploadDate = docUploadDate;
	}

	public UserRegistration getDocumentUser() {
		return documentUser;
	}

	public void setDocumentUser(UserRegistration documentUser) {
		this.documentUser = documentUser;
	}

	public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
}