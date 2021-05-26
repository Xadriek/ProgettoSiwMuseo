package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Column(nullable=false)
	private String nazionalità;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private Long telefono;
	
	@OneToMany(mappedBy="artista",cascade= CascadeType.ALL)
	private Map<Long,Opera> opere;
	
	@ManyToOne
	private Museo museo;

	
}