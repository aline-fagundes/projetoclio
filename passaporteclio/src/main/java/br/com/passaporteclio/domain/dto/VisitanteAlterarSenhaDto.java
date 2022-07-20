package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class VisitanteAlterarSenhaDto extends RepresentationModel<VisitanteAlterarSenhaDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String senhaAntiga;
	
	@NotBlank
	private String senhaNova;
	
	@NotBlank
	private String confirmaSenhaNova;
}
