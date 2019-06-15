package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	
	public void upload(Photo foto) {
		this.photoRepository.save(foto);
	}
	
	public List<Photo> getAllFoto() {
		return (List<Photo>)this.photoRepository.findAll();
	}
	
	public Photo getFoto(Long id) {
		return this.photoRepository.findById(id).get();
	}
	
	public List<Photo> getAllPhotoByAuthor(Photographer f) {
		return (List<Photo>)this.photoRepository.findByAuthor(f);
	}
	
	public List<Photo> getPhotosByAlbum(Album album) {
		return (List<Photo>)this.photoRepository.findByAlbum(album);
	}

}
