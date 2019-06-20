package com.silph.WebPhoto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.AlbumForm;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.AlbumValidator;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotographerService photographerService;
	@Autowired
	private AlbumValidator albumValidator;
	
	@RequestMapping(value = "/admin/newAlbum", method = RequestMethod.GET)
	public String newAlbumForm(Model model) {
		
		model.addAttribute("album", new AlbumForm());
		return "albumForm";
	}
	
	@RequestMapping(value = "/admin/newAlbum", method = RequestMethod.POST)
	public String createAlbum(@Valid @ModelAttribute("album") AlbumForm albumForm,
								@RequestParam("author") String author, 
								Model model, BindingResult bindingResult) {
		this.albumValidator.validate(albumForm, bindingResult);
		if(!bindingResult.hasErrors()) {
			Photographer photographer = this.photographerService.getByUsername(author);
			if (photographer != null) {
				Album newAlbum = new Album(albumForm.getName(), photographer);
				this.albumService.save(newAlbum);
				return ("redirect:/" + author);
			} else {
				model.addAttribute("errMsg", "Fotografo non esistente");
				return "albumForm";
			}
		} else {
			return "albumForm";
		}
		
	}
}
