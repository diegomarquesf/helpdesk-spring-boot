package br.com.diego.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.helpdesk.domain.Chamado;
import br.com.diego.helpdesk.repositories.ChamadoRepository;
import br.com.diego.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado! ID: " +id));
	}

}
