package it.uniroma3.siw.spring.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
public @Data class Collezione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	@Column(length=299)
	private String descrizione;
	
	@OneToMany(mappedBy="collezione",cascade = CascadeType.ALL)
	private List<Opera> opere;
	
	@ManyToOne
	private Curatore curatore;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	
	public Collezione(Long id, String nome, String descrizione, List<Opera> opere, Curatore curatore, String photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.opere = opere;
		this.curatore = curatore;
		this.photos = photos;
	}
	public Collezione() {
		super();
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"collezione-photos" + "/"+ id + "/" + photos;
    }














}
