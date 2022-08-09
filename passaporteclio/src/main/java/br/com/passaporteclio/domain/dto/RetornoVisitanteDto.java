package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
