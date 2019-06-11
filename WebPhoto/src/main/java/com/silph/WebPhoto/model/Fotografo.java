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
@Table(name="fotografi")
public class Fotografo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="autore")
	private List<Foto> fotografie;
	
	public Fotografo() {
		
	}
	
	public Fotografo(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public List<Foto> getFotografie() {
		return fotografie;
	}
	public void setFotografie(List<Foto> fotografie) {
		this.fotografie = fotografie;
	}
	
	


}
