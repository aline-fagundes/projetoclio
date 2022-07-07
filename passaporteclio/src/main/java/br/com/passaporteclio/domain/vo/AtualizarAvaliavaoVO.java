package br.com.passaporteclio.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AtualizarAvaliavaoVO extends RepresentationModel<AtualizarAvaliavaoVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotNull
	private Integer nota;

	@NotBlank
	private String avaliacao;

}
