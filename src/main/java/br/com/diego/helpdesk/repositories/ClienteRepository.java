package br.com.diego.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
