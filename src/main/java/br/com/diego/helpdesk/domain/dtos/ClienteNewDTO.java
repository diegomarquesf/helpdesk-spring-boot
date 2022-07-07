package br.com.diego.helpdesk.domain.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteNewDTO {
	
	@NotNull(message = "O campo NOME é requerido")
	protected String nome;
	
	@NotNull(message = "O campo CPF é requerido")
	@CPF
	protected String cpf;
	
	@NotNull(message = "O campo EMAIL é requerido")
	protected String email;
	
	@NotNull(message = "O campo SENHA é requerido")
	protected String senha;
	
	
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyy")
	protected LocalDate dataCriacao = LocalDate.now();
	
	

}
