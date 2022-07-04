package br.com.diego.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
