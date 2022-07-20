package br.com.passaporteclio.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriacaoPresencaDto extends RepresentationModel<CriacaoPresencaDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	private CriacaoPresencaMuseuDto museu;


	private LocalDateTime data;
	
}
