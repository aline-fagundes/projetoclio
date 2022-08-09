package br.com.passaporteclio.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class CriaPresencaDto extends RepresentationModel<CriaPresencaDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	private CriaPresencaMuseuDto museu;
	
	private LocalDateTime data;	
	
	private CriaPresencaUserDto autor;
}
