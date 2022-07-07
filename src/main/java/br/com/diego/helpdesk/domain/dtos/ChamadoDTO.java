package br.com.diego.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.diego.helpdesk.domain.Chamado;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ChamadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataFechamento;
	
	private Integer prioridade;
	
	private Integer status;
	
	private String titulo;
	
	private String observacoes;
	
	private Integer tecnico;

	private Integer cliente;

	private String nomeTecnico;
	
	private String nomeCliente;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.tecnico = chamado.getTecnico().getId();
		this.cliente = chamado.getCliente().getId();
		this.nomeCliente = chamado.getTecnico().getNome();
		this.nomeTecnico = chamado.getCliente().getNome();

	}
	
	
	
}
