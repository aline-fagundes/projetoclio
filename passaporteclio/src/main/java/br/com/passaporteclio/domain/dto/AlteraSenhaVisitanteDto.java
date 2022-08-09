package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class AlteraSenhaVisitanteDto extends RepresentationModel<AlteraSenhaVisitanteDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String senhaAntiga;
	
	@NotBlank
	private String senhaNova;
	
	@NotBlank
	private String confirmaSenhaNova;
}
