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
import org.springframework.web.servlet.ModelAndView;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.PhotographerRepository;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotoService;
import com.silph.WebPhoto.service.PhotoValidator;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class WebPhotoController {

	@Autowired
	private PhotographerService photographerService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotoValidator photoValidator;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/") 
	public String home(Model model) {
		model.addAttribute("photos", this.photoService.getAllFoto());
		model.addAttribute("photographers", this.photographerService.getListaFotografi());
		return "index.html";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPanel() {
		return "admin.html";
	}
	
	
	
	@RequestMapping("/{username}")
	public String getFotografo(@PathVariable("username") String username, Model model) {
		Photographer photographer = this.photographerService.getByUsername(username);
		model.addAttribute("photographer", photographer);
		model.addAttribute("photos", this.photoService.getAllPhotoByAuthor(photographer));
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
	
}
