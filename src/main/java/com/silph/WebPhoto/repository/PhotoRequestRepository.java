package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.WebPhoto.model.PhotoRequest;

@Repository
public interface PhotoRequestRepository extends CrudRepository<PhotoRequest, Long> {
	
}
