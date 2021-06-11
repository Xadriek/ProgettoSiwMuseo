package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Date dataDiNascita;
	

	@Column(nullable=false)
	private String luogoNascita;
	
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private Long telefono;
	
	@Column(nullable=false)
	private Long matricola;
	@OneToMany(mappedBy="curatore",cascade= {CascadeType.ALL})
	private Map<Long,Collezione> collezioni;
	

}
