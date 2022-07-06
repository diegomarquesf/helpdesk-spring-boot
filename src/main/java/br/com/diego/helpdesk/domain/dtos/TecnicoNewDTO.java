package br.com.diego.helpdesk.domain.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TecnicoNewDTO {
	
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyy")
	protected LocalDate dataCriacao = LocalDate.now();
	
	

}
