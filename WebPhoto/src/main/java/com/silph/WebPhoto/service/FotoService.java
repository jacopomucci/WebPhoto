package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Foto;
import com.silph.WebPhoto.repository.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	public void caricaFoto(Foto foto) {
		this.fotoRepository.save(foto);
	}
	
	public List<Foto> getAllFoto() {
		return (List<Foto>)this.fotoRepository.findAll();
	}

}
