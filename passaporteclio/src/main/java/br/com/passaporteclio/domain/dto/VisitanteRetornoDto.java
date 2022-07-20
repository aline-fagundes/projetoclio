package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class VisitanteRetornoDto extends RepresentationModel<VisitanteRetornoDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	private UserRetornoDto user;

}
