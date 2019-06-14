package com.silph.WebPhoto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;


@Repository
public interface FotoRepository extends CrudRepository<Photo, Long>{

	public List<Photo> findByAuthor(Photographer author);
	public List<Photo> findByAlbum(Album album);
}
