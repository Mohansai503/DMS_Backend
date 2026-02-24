package com.dms.dmsproject.service;

import java.util.List;

import com.dms.dmsproject.dto.ShareDocumentDTO;


public interface ShareDocumentService {

	ShareDocumentDTO getShareDetails(Integer shareId);
	List<ShareDocumentDTO> getReceivedDocuments(Integer userId);
}
