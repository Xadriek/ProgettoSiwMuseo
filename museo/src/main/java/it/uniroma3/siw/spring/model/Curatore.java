package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
	@Column(nullable=true)
	private Long telefono;
	
	@Column(nullable=false)
	private Long matricola;
	@OneToMany(mappedBy="curatore",cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private List<Collezione> collezioni;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"curatore-photos" + "/"+ id + "/" + photos;
    }

	
	public Curatore(Long id, String nome, String cognome, LocalDate dataDiNascita, String luogoNascita, String email,
			Long telefono, Long matricola, List<Collezione> collezioni, String photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoNascita = luogoNascita;
		this.email = email;
		this.telefono = telefono;
		this.matricola = matricola;
		this.collezioni = collezioni;
		this.photos = photos;
	}


	public Curatore() {
		super();
	}
	
	
}
