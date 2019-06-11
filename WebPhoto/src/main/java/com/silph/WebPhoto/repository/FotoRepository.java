package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.WebPhoto.model.Foto;


@Repository
public interface FotoRepository extends CrudRepository<Foto, Long>{

}
