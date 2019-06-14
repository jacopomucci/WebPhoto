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
import org.springframework.web.servlet.ModelAndView;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.FotografoRepository;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.FotoService;
import com.silph.WebPhoto.service.FotoValidator;
import com.silph.WebPhoto.service.FotografoService;

@Controller
public class WebPhotoController {

	@Autowired
	private FotografoService fotografoService;
	@Autowired
	private FotoService fotoService;
	@Autowired
	private FotoValidator fotoValidator;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/") 
	public String home(Model model) {
		model.addAttribute("photos", this.fotoService.getAllFoto());
		model.addAttribute("photographers", this.fotografoService.getListaFotografi());
		return "index.html";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPanel() {
		return "admin.html";
	}
	
	@RequestMapping("/allFotografi")
	public String getListaFotografi(Model model) {
		model.addAttribute("fotografi", this.fotografoService.getListaFotografi());
		return "fotografi.html";
	}
	
	@RequestMapping("/{username}")
	public String getFotografo(@PathVariable("username") String username, Model model) {
		Photographer photographer = this.fotografoService.getByUsername(username);
		model.addAttribute("photographer", photographer);
		model.addAttribute("photos", this.fotoService.getAllPhotoByAuthor(photographer));
		return "fotografo.html";
	}
	
	@RequestMapping("/newFotografo")
	public String newStudente(Model model) {
		model.addAttribute("fotografo", new Photographer());
		return "fotografoForm.html";
	}
	
	@RequestMapping("/addFotografo")
	public String addFotografo(@ModelAttribute("fotografo") Photographer fotografo, Model model) {
		this.fotografoService.inserisci(fotografo);
		return this.getListaFotografi(model);
	}
	
	@RequestMapping("/newFoto") 
	public String newFoto(Model model) {
		model.addAttribute("foto", new Photo());
		model.addAttribute("allFotografi", this.fotografoService.getListaFotografi());
		model.addAttribute("allAlbum", this.albumService.allAlbum());
		return "fotoForm.html";
	}
	
	@RequestMapping("/photo/{id}")
	public String getFoto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("photo", this.fotoService.getFoto(id));
		return "foto.html";
	}
	
	@RequestMapping("/album/{id}") 
	public String getAlbum(@PathVariable("id") Long id, Model model) {
		Album album = this.albumService.getAlbum(id);
		model.addAttribute("album", album);
		model.addAttribute("photos", this.fotoService.getPhotosByAlbum(album));
		return "album.html";
	}
	

	@RequestMapping(value = "/addFoto", method = RequestMethod.POST)
	public String addNewFoto(@ModelAttribute("foto") Photo foto, Model model) {
		
		this.fotoService.caricaFoto(foto);
		return home(model);
	}
}
