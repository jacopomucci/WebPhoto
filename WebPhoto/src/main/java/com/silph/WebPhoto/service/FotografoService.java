package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.FotografoRepository;

@Service
public class FotografoService {

	
	@Autowired
	private FotografoRepository fotografoRepository;

	
	public void inserisci(Photographer f) {
		this.fotografoRepository.save(f);
	}
	
	public List<Photographer> getListaFotografi() {
		return (List<Photographer>)this.fotografoRepository.findAll();
	}
	
	public Photographer getById(Long id) {
		return this.fotografoRepository.findById(id).get();
	}
	
	public Photographer getByUsername(String username) {
		return this.fotografoRepository.findByUsername(username);
	}
}
