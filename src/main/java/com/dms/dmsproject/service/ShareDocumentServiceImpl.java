package com.dms.dmsproject.service;
import com.dms.dmsproject.dto.ShareDocumentDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.ShareDocumentDAO;
import com.dms.dmsproject.dto.UserRegistrationDTO;
import com.dms.dmsproject.model.ShareDocument;
@Service
public class ShareDocumentServiceImpl implements ShareDocumentService {
	 @Autowired
	    private ShareDocumentDAO shareDocumentDAO;

	    @Override
	    public ShareDocumentDTO getShareDetails(Integer shareId) {

	        ShareDocument share = shareDocumentDAO.findById(shareId)
	                .orElseThrow(() -> new RuntimeException("Share ID not found"));

	        return mapToDTO(share);
	    }
	    
	    @Override
	    public List<ShareDocumentDTO> getReceivedDocuments(Integer userId) {

	        List<ShareDocument> shares =
	                shareDocumentDAO.findBySharedToUserId(userId);

	        return shares.stream()
	                .map(share -> {
	                    ShareDocumentDTO dto = mapToDTO(share);
	                    dto.setSharedTo(null);
	                    return dto;
	                })   
	                .toList();
	    }
	    
	    //Entity to DTO Conversion
	    
	    private ShareDocumentDTO mapToDTO(ShareDocument share) {

	        return new ShareDocumentDTO(
	                share.getShareId(),
	                share.getDate(),
	                new UserRegistrationDTO(
	                        share.getSharedBy().getUserId(),
	                        share.getSharedBy().getUserName(),
	                        share.getSharedBy().getUserEmailId()
	                ),
	                new UserRegistrationDTO(
	                        share.getSharedTo().getUserId(),
	                        share.getSharedTo().getUserName(),
	                        share.getSharedTo().getUserEmailId()
	                ),
	                share.getDocument().getDocId(),
	                share.getDocument().getDocumentType(),   // or getFileType()
	                share.getDocument().getDocName(),        // or getFileName()
	                share.getDocument().getDocSize()         // adjust method name
	        );
	    }

}
