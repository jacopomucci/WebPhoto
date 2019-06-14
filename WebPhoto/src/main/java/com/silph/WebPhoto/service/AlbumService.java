package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public Album salva(Album album) {
		return this.albumRepository.save(album);
	}
	
	public List<Album> allAlbum() {
		return (List<Album>)this.albumRepository.findAll();
	}
	
	public Album getAlbum(Long id) {
		return this.albumRepository.findById(id).get();
	}

}
