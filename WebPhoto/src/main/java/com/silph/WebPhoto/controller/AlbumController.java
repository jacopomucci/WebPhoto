package com.silph.WebPhoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotographerService photographerService;
	
	@RequestMapping(value = "/newAlbum", method = RequestMethod.GET)
	public String newPhotographer(Model model) {
		model.addAttribute("album", new Album());
		return "albumForm.html";
	}
	
	@RequestMapping(value = "/newAlbum", method = RequestMethod.POST)
	public String addFotografo(@ModelAttribute("album") Album album, Model model, WebRequest request) {
		Photographer photographer = this.photographerService.getByUsername(request.getParameter("username"));
		if (photographer != null) {
			album.setAuthor(photographer);
			this.albumService.save(album);
			return ("redirect:/" + photographer.getUsername() + "/album/" + album.getName());
		} else {
			model.addAttribute("errMsg", "Fotografo non esistente");
			return "albumForm";
		}
		
	}
}
