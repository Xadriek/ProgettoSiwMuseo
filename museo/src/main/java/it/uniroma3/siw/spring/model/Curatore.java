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
	@OneToMany(mappedBy="curatore",cascade= {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
	private List<Collezione> collezioni;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"curatore-photos" + "/"+ id + "/" + photos;
    }
	

}
