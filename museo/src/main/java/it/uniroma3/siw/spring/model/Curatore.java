package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Curatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	private LocalDate dataDiNascita;
	

	@Column(nullable=false)
	private String luogoNascita;
	
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private Long telefono;
	
	@Column(nullable=false)
	private Long matricola;
	
	
}
