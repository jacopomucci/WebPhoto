package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.PhotographerRepository;

@Service
public class PhotographerService {

	
	@Autowired
	private PhotographerRepository photographerRepository;

	
	public void save(Photographer f) {
		this.photographerRepository.save(f);
	}
	
	public List<Photographer> getAll() {
		return (List<Photographer>)this.photographerRepository.findAll();
	}
	
	public Photographer getById(Long id) {
		return this.photographerRepository.findById(id).get();
	}
	
	public Photographer getByUsername(String username) {
		return this.photographerRepository.findByUsername(username);
	}
}
