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

import com.silph.WebPhoto.model.Foto;
import com.silph.WebPhoto.model.Fotografo;
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
	
	@RequestMapping(value= {"/", "/home"}) 
	public String getIndex() {
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
	
	@RequestMapping("/fotografo/{id}")
	public String getFotografo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("fotografo", this.fotografoService.getById(id));
		return "fotografo.html";
	}
	
	@RequestMapping("/newFotografo")
	public String newStudente(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "fotografoForm.html";
	}
	
	@RequestMapping("/addFotografo")
	public String addFotografo(@ModelAttribute("fotografo") Fotografo fotografo, Model model) {
		this.fotografoService.inserisci(fotografo);
		return this.getListaFotografi(model);
	}
	
	@RequestMapping("/newFoto") 
	public String newFoto(Model model) {
		model.addAttribute("foto", new Foto());
		model.addAttribute("allFotografi", this.fotografoService.getListaFotografi());
		model.addAttribute("allAlbum", this.albumService.allAlbum());
		return "fotoForm.html";
	}
	
	@RequestMapping(value = "/addFoto", method = RequestMethod.POST)
	public String addNewFoto(@ModelAttribute("foto") Foto foto, Model model) {
		
		//model.addAttribute("allFoto", this.fotoService.getAllFoto());
		this.fotoService.caricaFoto(foto);
		return "home.html";
	}
}
