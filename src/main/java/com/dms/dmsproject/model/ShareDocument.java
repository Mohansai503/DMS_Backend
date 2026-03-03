package com.dms.dmsproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shere_document")
public class ShareDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shereId") //shereId
	private Integer shareId; 
	
	 @ManyToOne
	    @JoinColumn(name = "shered_by")
	    private UserRegistration sharedBy;

	    @ManyToOne
	    @JoinColumn(name = "shered_to")
	    private UserRegistration sharedTo;

	    @ManyToOne
	    @JoinColumn(name = "document_id")
	    private UploadResponse document;

	    @Column(name = "date")
	    private LocalDateTime date;

		public Integer getShareId() {
			return shareId;
		}

		public void setShareId(Integer shareId) {
			this.shareId = shareId;
		}

		public UserRegistration getSharedBy() {
			return sharedBy;
		}

		public void setSharedBy(UserRegistration sharedBy) {
			this.sharedBy = sharedBy;
		}

		public UserRegistration getSharedTo() {
			return sharedTo;
		}

		public void setSharedTo(UserRegistration sharedTo) {
			this.sharedTo = sharedTo;
		}

		public UploadResponse getDocument() {
			return document;
		}

		public void setDocument(UploadResponse document) {
			this.document = document;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}
	    
	    
}
