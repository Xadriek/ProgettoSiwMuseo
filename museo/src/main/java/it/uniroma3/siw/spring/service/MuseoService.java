package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Museo;
import it.uniroma3.siw.spring.repository.MuseoRepository;

@Service
public class MuseoService {
	
	private MuseoRepository museoRepository;
	
	@Transactional
	public Museo museoPerId(Long id) {
		Optional<Museo> optional = museoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public boolean alreadyExists(Museo museo) {
		List<Museo> musei = this.museoRepository.findByNome(museo.getNome());
		if (musei.size() > 0)
			return true;
		else 
			return false;
	}
}
