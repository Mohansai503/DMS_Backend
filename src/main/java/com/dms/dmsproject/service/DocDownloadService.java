package com.dms.dmsproject.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DocDownloadService {

    ResponseEntity<Resource> downloadDocument(Integer docId);
}
