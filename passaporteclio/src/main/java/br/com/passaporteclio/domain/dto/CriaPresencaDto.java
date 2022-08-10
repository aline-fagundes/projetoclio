package br.com.passaporteclio.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriaPresencaDto extends RepresentationModel<CriaPresencaDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	private CriaPresencaMuseuDto museu;
	
	private LocalDateTime data;	
	
	private CriaPresencaUserDto autor;
}
