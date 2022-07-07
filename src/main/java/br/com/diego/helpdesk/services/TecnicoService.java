package br.com.diego.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.helpdesk.domain.Pessoa;
import br.com.diego.helpdesk.domain.Tecnico;
import br.com.diego.helpdesk.domain.dtos.TecnicoDTO;
import br.com.diego.helpdesk.domain.dtos.TecnicoNewDTO;
import br.com.diego.helpdesk.repositories.PessoaRepository;
import br.com.diego.helpdesk.repositories.TecnicoRepository;
import br.com.diego.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.diego.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
	}


	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}


	public Tecnico create(Tecnico tecnico) {
		validaPorCPFEMAIL(tecnico);
		tecnico = tecnicoRepository.save(tecnico);
		return tecnico;
	}
	
	public Tecnico update(Tecnico tecnico) {
		Tecnico newTecnico = findById(tecnico.getId());
		validaPorCPFEMAIL(tecnico);
		updateData(newTecnico, tecnico);
		return tecnicoRepository.save(newTecnico);
	}
	
	public void updateData(Tecnico newTecnico, Tecnico tecnico) {
		newTecnico.setNome(tecnico.getNome());
		newTecnico.setCpf(tecnico.getCpf());
		newTecnico.setEmail(tecnico.getEmail());
		newTecnico.setSenha(tecnico.getSenha());
	}
	
	private void validaPorCPFEMAIL(Tecnico tecnico) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnico.getCpf());
		if(pessoa.isPresent() && pessoa.get().getId() != tecnico.getId() ) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		pessoa = pessoaRepository.findByEmail(tecnico.getEmail());
		if(pessoa.isPresent() && pessoa.get().getId() != tecnico.getId() ) {
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no sistema");
		}
	}
	
	public Tecnico fromDTO(TecnicoDTO tecnicoDTO) {
		return new Tecnico(tecnicoDTO.getId(), tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getEmail(), tecnicoDTO.getSenha());
	}

	public Tecnico fromDTO(TecnicoNewDTO newDTO) {
		Tecnico tecnico = new Tecnico(null, newDTO.getNome(), newDTO.getCpf(), newDTO.getEmail(), newDTO.getSenha());
		return tecnico;
	}



}
