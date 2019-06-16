package com.silph.WebPhoto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotoService;
import com.silph.WebPhoto.service.PhotoValidator;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotoValidator photoValidator;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotographerService photographerService;
	
	@RequestMapping(value = "/admin/uploadPhoto", method= RequestMethod.GET) 
	public String newFoto(Model model) {
		model.addAttribute("photo", new Photo());
		return "photoForm.html";
	}
	
	@RequestMapping(value = "/admin/uploadPhoto", method = RequestMethod.POST)
	public String uploadPhoto(@Valid @ModelAttribute("photo") Photo photo,
								BindingResult bindingResult,
								@RequestParam("author") String username,
								@RequestParam("album") String albumName,
								Model model, WebRequest request) {
		
		this.photoValidator.validate(photo, bindingResult);{
	
			Photographer author = this.photographerService.getByUsername(username);
			if (author != null) {
				Album album = this.albumService.getByAuthorAndName(author, albumName);
				if (album != null) {
					Photo newPhoto = new Photo(photo.getName(), photo.getDescription(), author, album);
					this.photoService.upload(newPhoto);
					return ("redirect:/photo/" + newPhoto.getId());
				} else {
					model.addAttribute("msg", "Questo fotografo non ha nessun album con questo nome");
					return newFoto(model);
				}
			} else {
				model.addAttribute("msg", "Non esiste nessun fotografo con questo username");
				return newFoto(model);
			}
		}

	}
	
	@RequestMapping("/request/{id}") 
	public String request(@PathVariable("id") Long id, Model model) {
		return "request";
	}
	
	
	@RequestMapping("/photo/{id}")
	public String getFoto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("photo", this.photoService.getFoto(id));
		return "photo.html";
	}
	
	@RequestMapping("/photos")
	public String photos(Model model) {
		return ("redirect:/");
	}
	
	
}
