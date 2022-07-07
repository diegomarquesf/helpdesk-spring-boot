package br.com.diego.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.helpdesk.domain.Cliente;
import br.com.diego.helpdesk.domain.Pessoa;
import br.com.diego.helpdesk.domain.dtos.ClienteDTO;
import br.com.diego.helpdesk.domain.dtos.ClienteNewDTO;
import br.com.diego.helpdesk.repositories.ClienteRepository;
import br.com.diego.helpdesk.repositories.PessoaRepository;
import br.com.diego.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.diego.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
	}


	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}


	public Cliente create(Cliente cliente) {
		validaPorCPFEMAIL(cliente);
		cliente = clienteRepository.save(cliente);
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = findById(cliente.getId());
		validaPorCPFEMAIL(cliente);
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}
	
	public void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setCpf(cliente.getCpf());
		newCliente.setEmail(cliente.getEmail());
		newCliente.setSenha(cliente.getSenha());
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Cliente possui ordens de chamado e não pode ser Excluido");
		}
	}
	
	
	private void validaPorCPFEMAIL(Cliente cliente) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cliente.getCpf());
		if(pessoa.isPresent() && pessoa.get().getId() != cliente.getId() ) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		pessoa = pessoaRepository.findByEmail(cliente.getEmail());
		if(pessoa.isPresent() && pessoa.get().getId() != cliente.getId() ) {
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no sistema");
		}
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getSenha());
	}

	public Cliente fromDTO(ClienteNewDTO newDTO) {
		Cliente cliente = new Cliente(null, newDTO.getNome(), newDTO.getCpf(), newDTO.getEmail(), newDTO.getSenha());
		return cliente;
	}






}
