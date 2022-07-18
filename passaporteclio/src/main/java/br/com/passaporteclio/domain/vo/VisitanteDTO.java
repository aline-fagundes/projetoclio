package br.com.passaporteclio.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class VisitanteDTO extends RepresentationModel<VisitanteDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@Valid
	private UserDTO user;

}
