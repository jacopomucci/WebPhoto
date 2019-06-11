package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.WebPhoto.model.Utente;

@Repository
public interface UserRepository extends CrudRepository<Utente, Long>{

}
