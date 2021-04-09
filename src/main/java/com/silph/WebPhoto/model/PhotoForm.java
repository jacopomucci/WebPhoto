package com.silph.WebPhoto.model;

public class PhotoForm {
	private String name;
	private String description;
	private String author;
	private String album;
	private String fileName;
	
	public PhotoForm () {}

	public PhotoForm(String name, String description, String author, String album) {
		super();
		this.name = name;
		this.description = description;
		this.author = author;
		this.album = album;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
