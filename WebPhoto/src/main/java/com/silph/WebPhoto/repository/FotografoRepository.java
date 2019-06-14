package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;

import com.silph.WebPhoto.model.Photographer;

public interface FotografoRepository extends CrudRepository<Photographer, Long>{

	public Photographer findByUsername(String username);
}
