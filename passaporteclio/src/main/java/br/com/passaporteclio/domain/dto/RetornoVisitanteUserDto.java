package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetornoVisitanteUserDto extends RepresentationModel<RetornoVisitanteUserDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;

	@NotBlank
	private String email;
}
