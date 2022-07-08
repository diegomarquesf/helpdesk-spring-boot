package br.com.diego.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.GET )
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> list = chamadoService.findAll();;
		List<ChamadoDTO> listDTO = list.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ChamadoDTO> create (@RequestBody ChamadoDTO chamadoDTO){
		Chamado chamado = chamadoService.create(chamadoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT )
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody @Valid ChamadoDTO chamadoDTO){
		Chamado newChamado = chamadoService.update(id, chamadoDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(newChamado));
	}
}
