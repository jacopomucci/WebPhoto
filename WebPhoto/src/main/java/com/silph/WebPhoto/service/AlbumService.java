package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public Album save(Album album) {
		return this.albumRepository.save(album);
	}
	
	public List<Album> allAlbum() {
		return (List<Album>)this.albumRepository.findAll();
	}
	
	public Album getById(Long id) {
		return this.albumRepository.findById(id).get();
	}
	
	public List<Album> getByAuthor(Photographer author) {
		return this.albumRepository.findByAuthor(author);
	}
	
	public Album getByAuthorAndName(Photographer author, String name) {
		List<Album> album = this.getByAuthor(author);
		Album albumR = null;
		for(Album a: album) {
			if(a.getName().equals(name)) {
				albumR = a;
			}
		}
		
		return albumR;
	}
}
