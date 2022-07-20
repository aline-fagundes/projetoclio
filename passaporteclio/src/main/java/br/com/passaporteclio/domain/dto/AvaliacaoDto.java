package br.com.passaporteclio.domain.dto;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AvaliacaoDto extends RepresentationModel<AvaliacaoDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	private int nota;

	@NotBlank
	private String avaliacao;
	
	@NotBlank
	private MuseusDto museu;
}
