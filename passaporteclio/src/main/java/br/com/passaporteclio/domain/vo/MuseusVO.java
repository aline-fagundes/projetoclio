package br.com.passaporteclio.domain.vo;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import java.io.Serializable;


import com.mysql.cj.jdbc.Blob;

import br.com.passaporteclio.domain.entity.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class MuseusVO extends RepresentationModel<MuseusVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	
	private String nome;
	private String descricaoMuseu;
	private String funcionamentoMuseu;
	private Blob fotoMuseu;
	private EnderecoVO endereco;
}
