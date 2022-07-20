package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
class CriaAvaliacaoMuseuDto extends RepresentationModel<CriaAvaliacaoMuseuDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
}