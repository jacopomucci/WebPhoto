package com.silph.WebPhoto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;

@Component
public class FotoValidator implements Validator{

	@Autowired
	private FotografoService fotografoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Photo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		}

}
