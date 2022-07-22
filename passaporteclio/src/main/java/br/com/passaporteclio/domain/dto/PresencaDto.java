package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PresencaDto extends RepresentationModel<PresencaDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank
	private MuseusDto museu;

	@NotNull
	private String data;
	
	private CriaPresencaUserDto autor;
}
