package com.dms.dmsproject.dao;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.dms.dmsproject.model.UploadResponse;
	public interface DocumentDAO extends JpaRepository<UploadResponse, Integer>{

	

}
