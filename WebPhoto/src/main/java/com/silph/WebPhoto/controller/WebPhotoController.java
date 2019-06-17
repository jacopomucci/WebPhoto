package com.silph.WebPhoto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoRequest;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.PhotographerRepository;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotoRequestService;
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
	private PhotoRequestService photoRequestService;
	
	
	
	@RequestMapping("/") 
	public String home(HttpServletRequest request, Model model) {
		model.addAttribute("photos", this.photoService.getAllFoto());
		model.addAttribute("photographers", this.photographerService.getListaFotografi());
		request.getSession().setAttribute("photosRequested", new ArrayList<Photo>());
		return "index.html";
	}
	
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = details.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("username", details.getUsername());
		model.addAttribute("role", role);
		
		return "welcome";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = details.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("username", details.getUsername());
		model.addAttribute("role", role);
		return "admin";
	}
	
}
