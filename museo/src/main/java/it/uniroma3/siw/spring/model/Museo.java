package it.uniroma3.siw.spring.model;

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
public  @Data class Museo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String indirizzo;
	
	@OneToMany(mappedBy = "museo", cascade = CascadeType.ALL)
	private Map<Long,User> users;
	
	@OneToMany(mappedBy = "museo",cascade = CascadeType.ALL)
	private Map<Long,Artista> artisti;
	
	@OneToMany(mappedBy = "museo",cascade = CascadeType.ALL)
	private Map<Long,Collezione> collezioni;
	
	@OneToMany(mappedBy = "museo",cascade = CascadeType.ALL)
	private Map<Long,Curatore> curatori;
}
