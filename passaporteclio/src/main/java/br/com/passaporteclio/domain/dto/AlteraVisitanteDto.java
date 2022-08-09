package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlteraVisitanteDto extends RepresentationModel<AlteraVisitanteDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
}
