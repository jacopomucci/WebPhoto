package com.silph.WebPhoto.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoForm;

@Component
public class PhotoValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "album", "required");
		//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileName", "required");
			
		}

}
