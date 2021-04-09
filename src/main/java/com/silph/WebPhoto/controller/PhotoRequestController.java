package com.silph.WebPhoto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoRequest;
import com.silph.WebPhoto.service.PhotoRequestService;
import com.silph.WebPhoto.service.PhotoService;

@Controller
@SessionAttributes("photoRequest")
public class PhotoRequestController {
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotoRequestService photoRequestService;

	@ModelAttribute("photoRequest")
	public PhotoRequest photoRequest() {
		return new PhotoRequest();
	}

	@RequestMapping(value = "/photoRequest", method = RequestMethod.GET)
	public String addPhotoToRequest(@ModelAttribute("photoRequest") PhotoRequest photoRequest,
								@RequestParam("photoRequested") Long photoId, Model model) {
		
		Photo photo = this.photoService.getFoto(photoId);
		
		if(photoRequest != null) {
			photoRequest.getPhotos().add(photo);
			model.addAttribute("photoRequest", photoRequest);
			model.addAttribute("photos", photoRequest.getPhotos());
		} else {
			PhotoRequest newPhotoRequest = new PhotoRequest();
			newPhotoRequest.getPhotos().add(photo);
			model.addAttribute("photoRequest", newPhotoRequest);
			model.addAttribute("photos", newPhotoRequest.getPhotos());
		}
		
		return "photoRequestForm.html";
	}
	
	
	@RequestMapping(value = "/photoRequest", method= RequestMethod.POST) 
	public String sendRequest(@ModelAttribute("photoRequest") PhotoRequest photoRequest, Model model) {
		photoRequestService.save(photoRequest);
		PhotoRequest newPhotoRequest = new PhotoRequest();
		model.addAttribute("photoRequest", newPhotoRequest);
		model.addAttribute("alert-success", "true");
		return "requestConfirm";
	}
	
	@RequestMapping(value = "/admin/requests", method = RequestMethod.GET) 
	public String viewRequests(Model model) {
		model.addAttribute("requests", this.photoRequestService.findAll());
		return "requests.html";
	}
	
	@RequestMapping(value = "/admin/requests", method = RequestMethod.POST) 
	public String acceptRequest(@RequestParam("request") Long id, Model model) {
		PhotoRequest pr = this.photoRequestService.findById(id);
		this.photoRequestService.remove(pr);
		model.addAttribute("requests", this.photoRequestService.findAll());
		return "requests.html";
	}
	
	@GetMapping("/photoRequest/{id}")
	public String photoRequestDetails(@PathVariable("id") Long id, Model model) {
		PhotoRequest pr = this.photoRequestService.findById(id);
		model.addAttribute("photoRequest", pr);
		model.addAttribute("photos", pr.getPhotos());
		return "photoRequest";
	}
		
}
