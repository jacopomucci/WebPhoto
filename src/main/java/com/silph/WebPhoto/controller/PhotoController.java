package com.silph.WebPhoto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.PhotoForm;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.service.AlbumService;
import com.silph.WebPhoto.service.PhotoService;
import com.silph.WebPhoto.service.PhotoValidator;
import com.silph.WebPhoto.service.PhotographerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		model.addAttribute("photoForm", new PhotoForm());
		return "uploadPhotoForm";
	}
	
	@RequestMapping(value = "/admin/uploadPhoto", method = RequestMethod.POST)
	public String uploadPhoto(@Valid @ModelAttribute("photoForm") PhotoForm photoForm,
								Model model, BindingResult bindingResult,
								@RequestParam("imageFile") MultipartFile imageFile) {
		
 		this.photoValidator.validate(photoForm, bindingResult);
 		if(!bindingResult.hasErrors()) {
			Photographer author = this.photographerService.getByUsername(photoForm.getAuthor());
			if (author != null) {
				Album album = this.albumService.getByAuthorAndName(author, photoForm.getAlbum());
				if (album != null) {
					Photo photo = new Photo(photoForm.getName(), photoForm.getDescription(), author, album, 0);
					photo.setFileName(imageFile.getOriginalFilename());
					try {
						photoService.savePhotoImage(imageFile, photo);
					} catch (Exception e) {
						e.printStackTrace();
						//log.error("Error saving photo", e);
						model.addAttribute("uploadErr", "Errore nel salvataggio della foto");
						return "uploadPhotoForm";
					}
					this.photoService.save(photo);
					return ("redirect:/photo/" + photo.getId());
				} else {
					model.addAttribute("albumErr", "Questo fotografo non ha nessun album con questo nome");
					return newFoto(model);
				}
			} else {
				model.addAttribute("photographerErr", "Non esiste nessun fotografo con questo username");
				return newFoto(model);
			}
	 	} else {
	 		return "uploadPhotoForm";
	 	}
	}

	@RequestMapping(value = "/admin/uploadImage", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("imageFile") MultipartFile imageFile, 
    									@RequestParam("name") String name, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Photo photo = new Photo();
		photo.setName(name);
		photo.setFileName(imageFile.getOriginalFilename());
		try {
			photoService.savePhotoImage(imageFile, photo);
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Error saving photo", e);
			 modelAndView.setViewName("error");
			 return modelAndView;
		}
    	
		photoService.save(photo);
    	modelAndView.setViewName("success");
    	modelAndView.addObject("photo", photo);
    	return modelAndView;
    	
    }

	@PostMapping("/photos/{id}/like")
	@ResponseBody()
	public int likePhoto(@PathVariable("id") Long photoId) {
		Photo photo = this.photoService.getFoto(photoId);
		int updatedLikes = photo.getLikes() + 1;
		photo.setLikes(updatedLikes);
		this.photoService.save(photo);
		return updatedLikes;
	}

	@GetMapping("/photos")
	public ModelAndView getListOfPhotos(@RequestParam("q") String query) {
		List<Photo> photos = this.photoService.getAllFoto();
		List<Photo> filteredPhotos = photos.stream().filter(p -> {
			boolean t = p.getName().toLowerCase().contains(query.toLowerCase());
			return t;
		}).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("fragments/photos-grid");
		mv.addObject("photos", filteredPhotos);
		return mv;
	}

	@RequestMapping("/photo/{id}")
	public String getPhoto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("photo", this.photoService.getFoto(id));
		return "photo";
	}
	
	@RequestMapping("/photos")
	public String photos(Model model) {
		return ("redirect:/");
	}
	
}
