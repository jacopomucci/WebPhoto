package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;

import com.silph.WebPhoto.model.Photographer;

public interface PhotographerRepository extends CrudRepository<Photographer, Long>{

	public Photographer findByUsername(String username);
}
