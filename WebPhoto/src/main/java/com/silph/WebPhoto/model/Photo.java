package com.silph.WebPhoto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	
	@ManyToOne
	private Photographer author;
	@ManyToOne
	private Album album;
	
	public Photo() {
		
	}
	
	public Photo(String name, String description, Photographer author, Album album) {
		this.name = name;
		this.description = description;
		this.author = author;
		this.album = album;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public void setAuthor(Photographer author) {
		this.author = author;
	}

	public Photographer getAuthor() {
		return author;
	}
	public void setAutore(Photographer author) {
		this.author = author;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}

}
