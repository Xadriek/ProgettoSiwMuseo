package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
public @Data class Opera {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	@Getter
	@Setter
	@Column(nullable=false)
	private String titolo;
	@Getter
	@Setter
	@Column(nullable=false, length=4)
	private Long anno;
	@Getter
	@Setter
	@Column(length=299)
	private String descrizione;
	@Getter
	@Setter
	@ManyToOne
	private Collezione collezione;
	@Getter
	@Setter
	@ManyToOne
	private Artista artista;

	
}
