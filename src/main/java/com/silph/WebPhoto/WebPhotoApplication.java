package com.silph.WebPhoto;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silph.WebPhoto.controller.FileUploadController;

@SpringBootApplication
public class WebPhotoApplication {
	
	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(WebPhotoApplication.class, args);
	}

}
