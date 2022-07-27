package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class EnderecoDto extends RepresentationModel<EnderecoDto> implements Serializable{

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
