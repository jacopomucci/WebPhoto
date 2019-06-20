package com.silph.WebPhoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotoService;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class PhotographerController {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotographerService photographerService;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/{username}")
	public String getFotografo(@PathVariable("username") String username, Model model) {
		Photographer photographer = this.photographerService.getByUsername(username);
		model.addAttribute("photographer", photographer);
		model.addAttribute("photos", this.photoService.getPhotosByAuthor(photographer));
		model.addAttribute("album", this.albumService.getByAuthor(photographer));
		return "photographer";
	}
	
	@RequestMapping("/{username}/album/{name}") 
	public String getAlbum(@PathVariable("username") String username,
							@PathVariable("name") String albumName, Model model) {
		Photographer author = photographerService.getByUsername(username);
		Album album = this.albumService.getByAuthorAndName(author, albumName);
		if (album != null) {
			model.addAttribute("album", album);
			model.addAttribute("photos", this.photoService.getPhotosByAlbum(album));
			return "album.html";
		} else {
			return "NotFound.html";
		}
	}
	
	@RequestMapping("photographers")
	public String getListaFotografi(Model model) {
		model.addAttribute("photographers", this.photographerService.getAll());
		return "photographers";
	}
	
	@RequestMapping(value = "/admin/newPhotographer", method = RequestMethod.GET)
	public String newPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "photographerForm";
	}
	
	@RequestMapping(value = "/admin/newPhotographer", method = RequestMethod.POST)
	public String addFotografo(@ModelAttribute("photographer") Photographer photographer, Model model) {
		this.photographerService.save(photographer);
		return ("redirect:/" + photographer.getUsername());
	}
	
}
