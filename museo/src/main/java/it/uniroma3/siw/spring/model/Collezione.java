package it.uniroma3.siw.spring.model;



import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy="collezione",cascade= {CascadeType.MERGE})
	private Map<Long,Opera> opere;
	
	@ManyToOne
	private Curatore curatore;

}
