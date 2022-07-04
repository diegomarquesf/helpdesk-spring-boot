package br.com.diego.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.helpdesk.domain.Chamado;
import br.com.diego.helpdesk.domain.Cliente;
import br.com.diego.helpdesk.domain.Tecnico;
import br.com.diego.helpdesk.domain.enums.Perfil;
import br.com.diego.helpdesk.domain.enums.Prioridade;
import br.com.diego.helpdesk.domain.enums.Status;
import br.com.diego.helpdesk.repositories.ChamadoRepository;
import br.com.diego.helpdesk.repositories.ClienteRepository;
import br.com.diego.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public void instaciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Diego Marques", "41652190880", "fariia_di@hotmail.com", "123");
		t1.addPerfil(Perfil.ADMIN);
		
		Cliente c1 = new Cliente(null, "Cliente 1", "11111111111", "cliente1@gmail.com", "123");
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
		
	}

}
