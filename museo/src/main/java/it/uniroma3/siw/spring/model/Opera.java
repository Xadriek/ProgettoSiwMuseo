package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Opera {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String titolo;
	
	@Column(length=4)
	private Long anno;
	
	@Column(length=299)
	private String descrizione;

	public Opera(Long id, String titolo, Long anno, String descrizione) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.anno = anno;
		this.descrizione = descrizione;
	}
	
}
