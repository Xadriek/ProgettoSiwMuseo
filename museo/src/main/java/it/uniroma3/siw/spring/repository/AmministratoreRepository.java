package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Amministratore;


public interface AmministratoreRepository extends CrudRepository<Amministratore, Long> {
	
	public List<Amministratore> findByNome(String nome);

	public List<Amministratore> findByNomeAndCognome(String nome, String cognome);

	public List<Amministratore> findByNomeOrCognome(String nome, String cognome);

	public List<Amministratore> findByUsername(String username);

}
