package com.silph.WebPhoto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.silph.WebPhoto.service.StorageService;


@Controller
public class FileUploadController {

	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
	

    
    @PostMapping("/uploadPhoto1")
    public String handleFileUpload(@RequestParam("photo") MultipartFile image,
            RedirectAttributes redirectAttributes, Model model) {
    	StringBuilder fileNames = new StringBuilder();
    	Path fileNameAndPath = Paths.get(uploadDirectory, image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        try {
			Files.write(fileNameAndPath, image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("msg", "Successufuly uploaded files" + fileNames.toString());
        return "index.html";
    	//storageService.saveImage(image);
       // redirectAttributes.addFlashAttribute("message",
      //          "You successfully uploaded " + image.getOriginalFilename() + "!");

       // return "redirect:/";
    }

}