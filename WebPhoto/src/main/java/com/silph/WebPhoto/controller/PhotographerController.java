package com.silph.WebPhoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.PhotographerService;

@Controller
public class PhotographerController {

	@Autowired
	private PhotographerService photographerService;
	
	
	@RequestMapping("/photographers")
	public String getListaFotografi(Model model) {
		model.addAttribute("photographers", this.photographerService.getListaFotografi());
		return "photographers";
	}
	
	@RequestMapping(value = "/newPhotographer", method = RequestMethod.GET)
	public String newPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "photographerForm.html";
	}
	
	@RequestMapping(value = "/newPhotographer", method = RequestMethod.POST)
	public String addFotografo(@ModelAttribute("photographer") Photographer photographer, Model model) {
		this.photographerService.inserisci(photographer);
		return ("redirect:/" + photographer.getUsername());
	}
	
}
