package br.com.passaporteclio.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper=false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto extends RepresentationModel<EnderecoDto> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	@Size(max=8)
	private String cep;
	
	@NotBlank
	@Size(max=45)
	private String rua;
	
	@NotNull
	private Integer numero;
	
	@NotBlank
	@Size(max=45)
	private String bairro;
	
	@NotBlank
	@Size(max=45)
	private String cidade;
	
	@NotBlank
	@Size(max=2)
	private String estado;
	
	@NotBlank
	@Size(max=45)
	private String pais;
}
