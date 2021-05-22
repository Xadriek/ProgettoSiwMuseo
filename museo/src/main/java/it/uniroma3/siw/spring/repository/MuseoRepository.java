package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Museo;


public interface MuseoRepository extends CrudRepository<Museo, Long> {
	
	public List<Museo> findByNome(String nome);


}
