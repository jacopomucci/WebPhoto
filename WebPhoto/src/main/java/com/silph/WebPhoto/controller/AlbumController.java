package com.silph.WebPhoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/admin/newAlbum", method = RequestMethod.GET)
	public String newAlbumForm(Model model) {
		
		return "albumForm";
	}
	
	@RequestMapping(value = "/admin/newAlbum", method = RequestMethod.POST)
	public String createAlbum( @RequestParam("name") String albumName,
								@RequestParam("author") String author, 
								Model model) {
		
		Photographer photographer = this.photographerService.getByUsername(author);
		if (photographer != null) {
			Album album = new Album(albumName, photographer);
			this.albumService.save(album);
			return ("redirect:/" + author + "/album/" + albumName);
		} else {
			model.addAttribute("errMsg", "Fotografo non esistente");
			return "albumForm";
		}
		
	}
}
