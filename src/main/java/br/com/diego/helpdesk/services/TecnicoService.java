package br.com.diego.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.helpdesk.domain.Tecnico;
import br.com.diego.helpdesk.domain.dtos.TecnicoNewDTO;
import br.com.diego.helpdesk.repositories.TecnicoRepository;
import br.com.diego.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
	}


	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}


	public Tecnico create(Tecnico tecnico) {
		tecnico = tecnicoRepository.save(tecnico);
		return tecnico;
	}
	
	public Tecnico fromDTO(TecnicoNewDTO newDTO) {
		Tecnico tecnico = new Tecnico(null, newDTO.getNome(), newDTO.getCpf(), newDTO.getEmail(), newDTO.getSenha());
		return tecnico;
	}
}
