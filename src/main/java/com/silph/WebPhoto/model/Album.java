package com.silph.WebPhoto.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne
	private Photographer author;
	
	@OneToMany(mappedBy="album")
	private List<Photo> photos;
	
	public Album() {
	}
	
	public Album(String name, Photographer author) {
		this.name = name;
		this.author = author;
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
	
	public void setName(String nome) {
		this.name = nome;
	}

	public Photographer getAuthor() {
		return author;
	}

	public void setAuthor(Photographer author) {
		this.author = author;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> fotografie) {
		this.photos = fotografie;
	}
	
}
