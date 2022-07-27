package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RetornoVisitanteDto extends RepresentationModel<RetornoVisitanteDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@Valid
	private RetornoVisitanteUserDto user;
}
