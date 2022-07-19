package br.com.passaporteclio.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class VisitanteAlterarDto extends RepresentationModel<VisitanteAlterarDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
}
