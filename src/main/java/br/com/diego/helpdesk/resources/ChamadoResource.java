package br.com.diego.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.helpdesk.domain.Chamado;
import br.com.diego.helpdesk.domain.dtos.ChamadoDTO;
import br.com.diego.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET )
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado chamado = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	

}
