package br.com.passaporteclio.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper=false)
@Data
public class EnderecoVO extends RepresentationModel<EnderecoVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String cep;
	
	@NotBlank
	private String rua;
	
	@NotNull
	private Integer numero;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String estado;
	
	@NotBlank
	private String pais;
}
