package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoRequest;
import com.silph.WebPhoto.repository.PhotoRepository;
import com.silph.WebPhoto.repository.PhotoRequestRepository;

@Service
public class PhotoRequestService {
	
	@Autowired
	private PhotoRequestRepository photoRequestRepository;
	
	public PhotoRequest save(PhotoRequest phr) {
		return this.photoRequestRepository.save(phr);
	}
	
	public void remove(PhotoRequest phr) {
		this.photoRequestRepository.delete(phr);
	}
	
	public PhotoRequest findById(Long id) {
		return this.photoRequestRepository.findById(id).get();
	}
	
	public List<PhotoRequest> findAll() {
		return (List<PhotoRequest>) this.photoRequestRepository.findAll();
	}
	
	
	
}
