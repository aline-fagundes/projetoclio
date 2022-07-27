package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CriaAvaliacaoDto extends RepresentationModel<CriaAvaliacaoDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	private Integer nota;
	
	@NotBlank
	@Size(max=300)
	private String avaliacao;
	
	@NotNull
	private CriaAvaliacaoMuseuDto museu;
	
	private CriaAvaliacaoUserDto autor;
	
	private boolean denunciada;	
}