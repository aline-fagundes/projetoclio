package br.com.passaporteclio.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class MuseusDto extends RepresentationModel<MuseusDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String descricaoMuseu;

	@NotBlank
	private String funcionamentoMuseu;

	@NotBlank
	private String urlFoto;

	private String urlSite;

	@NotBlank
	private String urlInstagram;

	@Valid
	private EnderecoDto endereco;
}
