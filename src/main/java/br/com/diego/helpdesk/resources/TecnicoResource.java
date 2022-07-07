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

import br.com.diego.helpdesk.domain.Tecnico;
import br.com.diego.helpdesk.domain.dtos.TecnicoDTO;
import br.com.diego.helpdesk.domain.dtos.TecnicoNewDTO;
import br.com.diego.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Tecnico> create(@Valid @RequestBody TecnicoNewDTO newDTO){
		Tecnico tecnico = tecnicoService.fromDTO(newDTO);
		tecnico = tecnicoService.create(tecnico);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath() 
				.path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico tecnico = tecnicoService.fromDTO(tecnicoDTO);
		tecnico.setId(id);
		tecnico = tecnicoService.update(tecnico);
	 return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
		
}