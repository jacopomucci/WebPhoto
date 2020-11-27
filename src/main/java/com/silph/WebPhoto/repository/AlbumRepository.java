package com.silph.WebPhoto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photographer;

public interface AlbumRepository extends CrudRepository<Album, Long> {

	public Album findByName(String name);
	public List<Album> findByAuthor(Photographer author);
}
