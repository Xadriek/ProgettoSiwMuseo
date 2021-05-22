package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Amministratore;
import it.uniroma3.siw.spring.repository.AmministratoreRepository;

@Service
public class AmministratoreService {
	
	@Autowired
	private AmministratoreRepository amministratoreRepository; 
	
	@Transactional
	public Amministratore inserisci(Amministratore amministratore) {
		return amministratoreRepository.save(amministratore);
	}
	
	@Transactional
	public List<Amministratore> amministratoriPerNomeAndCognome(String nome, String cognome) {
		return amministratoreRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public List<Amministratore> tutti() {
		return (List<Amministratore>) amministratoreRepository.findAll();
	}

	@Transactional
	public Amministratore amministratorePerId(Long id) {
		Optional<Amministratore> optional = amministratoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public Amministratore amministratorePerUseranme(String username) {
		Optional<List<Amministratore>> optional = Optional.of(amministratoreRepository.findByUsername(username));
		if (optional.isPresent())
			return (Amministratore) optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Amministratore amministratore) {
		List<Amministratore> amministratori = this.amministratoreRepository.findByNomeAndCognome(amministratore.getNome(), amministratore.getCognome());
		if (amministratori.size() > 0)
			return true;
		else 
			return false;
	}
}
