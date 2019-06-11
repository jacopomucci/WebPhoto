package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Foto;
import com.silph.WebPhoto.model.Fotografo;
import com.silph.WebPhoto.repository.FotografoRepository;

@Service
public class FotografoService {

	
	@Autowired
	private FotografoRepository fotografoRepository;

	
	public void inserisci(Fotografo f) {
		this.fotografoRepository.save(f);
	}
	
	public List<Fotografo> getListaFotografi() {
		return (List<Fotografo>)this.fotografoRepository.findAll();
	}
	
	public Fotografo getById(Long id) {
		return this.fotografoRepository.findById(id).get();
	}
}
