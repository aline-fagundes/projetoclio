package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriaPresencaMuseuDto extends RepresentationModel<CriaPresencaMuseuDto> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
}


