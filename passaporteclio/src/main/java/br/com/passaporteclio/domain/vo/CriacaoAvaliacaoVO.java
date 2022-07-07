package br.com.passaporteclio.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CriacaoAvaliacaoVO extends RepresentationModel<CriacaoAvaliacaoVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	private Integer nota;
	
	@NotBlank
	private String avaliacao;
	
	@NotNull
	private CriacaoAvaliacaoMuseuVO museu;

}

@EqualsAndHashCode(callSuper = false)
@Data
class CriacaoAvaliacaoMuseuVO extends RepresentationModel<CriacaoAvaliacaoMuseuVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

}