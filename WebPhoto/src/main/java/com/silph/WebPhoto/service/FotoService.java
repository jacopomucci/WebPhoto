package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	public void caricaFoto(Photo foto) {
		this.fotoRepository.save(foto);
	}
	
	public List<Photo> getAllFoto() {
		return (List<Photo>)this.fotoRepository.findAll();
	}
	
	public Photo getFoto(Long id) {
		return this.fotoRepository.findById(id).get();
	}
	
	public List<Photo> getAllPhotoByAuthor(Photographer f) {
		return (List<Photo>)this.fotoRepository.findByAuthor(f);
	}
	
	public List<Photo> getPhotosByAlbum(Album album) {
		return (List<Photo>)this.fotoRepository.findByAlbum(album);
	}

}
