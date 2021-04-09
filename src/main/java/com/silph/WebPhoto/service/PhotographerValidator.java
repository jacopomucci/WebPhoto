package com.silph.WebPhoto.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoForm;
import com.silph.WebPhoto.model.Photographer;

@Component
public class PhotographerValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Photographer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
	}
}
