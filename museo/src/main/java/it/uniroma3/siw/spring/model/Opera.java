package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
public @Data class Opera {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	
	@Column(nullable=false)
	private String titolo;
	
	@Column(nullable=false, length=4)
	private Long anno;
	
	@Column(length=299)
	private String descrizione;
	
	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne
	private Artista artista;
	
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	public Opera(Long id, String titolo, Long anno, String descrizione, Collezione collezione, Artista artista,
			String photos) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.anno = anno;
		this.descrizione = descrizione;
		this.collezione = collezione;
		this.artista = artista;
		this.photos = photos;
	}
	
	public Opera() {
		super();
	}
	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"opera-photos" + "/"+ id + "/" + photos;
    }


	
}
