package com.silph.WebPhoto.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private ServletContext context;
	
	public void save(Photo photo) {
		this.photoRepository.save(photo);
	}
	
	
	public void savePhotoImage(MultipartFile imageFile, Photo photo) throws Exception {
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		photo.setPath(absolutePath + "/src/main/resources/static/uploads/");
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}
	
	public List<Photo> getAllFoto() {
		List<Photo> photos = (List<Photo>)this.photoRepository.findAll();
		return this.getOrderedPhotos(photos);
	}
	
	public Photo getFoto(Long id) {
		return this.photoRepository.findById(id).get();
	}
	
	public List<Photo> getByName (String name) {
		return this.photoRepository.findByName(name);
	}
	
	public List<Photo> getPhotosByAuthor(Photographer f) {
		List<Photo> photos = (List<Photo>)this.photoRepository.findByAuthor(f);
		return this.getOrderedPhotos(photos);
		
	}
	
	public List<Photo> getPhotosByAlbum(Album album) {
		return (List<Photo>)this.photoRepository.findByAlbum(album);
	}
	
	public List<Photo> getOrderedPhotos(List<Photo> photos) {
		Collections.sort(photos, Collections.reverseOrder());
		return photos;	
	}

	

}
