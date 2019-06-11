package com.silph.WebPhoto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@ManyToOne
	private Fotografo autore;
	@ManyToOne
	private Album album;
	
	public Foto() {
		
	}
	
	public Foto(String nome, Fotografo fotografo, Album album) {
		this.nome = nome;
		this.autore = fotografo;
		this.album = album;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Fotografo getAutore() {
		return autore;
	}
	public void setAutore(Fotografo autore) {
		this.autore = autore;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}

}
