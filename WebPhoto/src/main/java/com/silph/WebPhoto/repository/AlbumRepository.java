package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;

import com.silph.WebPhoto.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
