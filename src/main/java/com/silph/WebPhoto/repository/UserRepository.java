package com.silph.WebPhoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.WebPhoto.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
