package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
	@Column(nullable=true)
	private LocalDate dataDiMorte;
	

	@Column(nullable=false)
	private String luogoNascita;
	@Column(nullable=true)
	private String luogoMorte;
	
	@Column(nullable=false)
	private String nazionalita;
	
	@Column(nullable=false)
	private String descrizione;
	
	@OneToMany(mappedBy="artista",cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Opera> opere;
	
	@Column(nullable = true, length = 64)
    private String photos;
	


	public Artista(Long id, String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiMorte,
			String luogoNascita, String luogoMorte, String nazionalita, String descrizione, List<Opera> opere,
			String photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.dataDiMorte = dataDiMorte;
		this.luogoNascita = luogoNascita;
		this.luogoMorte = luogoMorte;
		this.nazionalita = nazionalita;
		this.descrizione = descrizione;
		this.opere = opere;
		this.photos = photos;
	}
	


	@Transient
	public String getPhotosImagePath() {
		if (this.getPhotos() == null || this.getId() == null) return null;
		
		return "/"+"artista-photos" + "/"+ id + "/" + photos;
	}



	public Artista() {
		super();
	}
	
}
