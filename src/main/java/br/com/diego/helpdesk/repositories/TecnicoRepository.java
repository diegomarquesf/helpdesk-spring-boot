package br.com.diego.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
