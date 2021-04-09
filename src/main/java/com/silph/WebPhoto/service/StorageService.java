package com.silph.WebPhoto.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StorageService {

    public void saveImage(MultipartFile imageFile){
//    	byte[] bytes = imageFile.getBytes();
//    	Path path = Paths.get(folder + imageFile.getOriginalFilename());
//    	Files.write(path, bytes);
    }

}

