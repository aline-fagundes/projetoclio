package br.com.passaporteclio.domain.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitanteDto extends RepresentationModel<VisitanteDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@Valid
	private UserDto user;
}
