package com.silph.WebPhoto.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silph.WebPhoto.model.Photo;

@Component
public class PhotoValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Photo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
			
			
		}

}
