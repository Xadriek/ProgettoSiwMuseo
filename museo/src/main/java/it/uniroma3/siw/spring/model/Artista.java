package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	private LocalDate dataDiNascita;
	
	private LocalDate dataDiMorte;
	

	@Column(nullable=false)
	private String luogoNascita;
	
	private String luogoMorte;

	private String nazionalità;

	private String email;
	
	private Long telefono;

	public Artista(String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiMorte, String luogoNascita,
			String luogoMorte, String nazionalità, String email, Long telefono) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.dataDiMorte = dataDiMorte;
		this.luogoNascita = luogoNascita;
		this.luogoMorte = luogoMorte;
		this.nazionalità = nazionalità;
		this.email = email;
		this.telefono = telefono;
	}
}