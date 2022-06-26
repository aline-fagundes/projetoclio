package br.com.passaporteclio.domain.vo;

import com.mysql.cj.jdbc.Blob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper=false)
@Data
public class MuseusVO extends RepresentationModel<MuseusVO> implements Serializable {
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricaoMuseu;
	@NotBlank
	private String funcionamentoMuseu;

	private Blob fotoMuseu;

	@Valid
	private EnderecoVO endereco;
}