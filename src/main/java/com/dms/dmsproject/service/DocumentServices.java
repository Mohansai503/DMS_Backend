
	package com.dms.dmsproject.service;

	import java.util.List;
	import com.dms.dmsproject.model.Document;

	public interface DocumentServices {

		void deleteDm(int documentId);
		List<Document> list(int userId);
		
		

	}
