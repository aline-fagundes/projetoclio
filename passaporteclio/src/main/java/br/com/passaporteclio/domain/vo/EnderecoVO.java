package br.com.passaporteclio.domain.vo;

import java.io.Serializable;


import org.springframework.hateoas.RepresentationModel;
import com.github.dozermapper.core.Mapping;



import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class EnderecoVO extends RepresentationModel<EnderecoVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	

	private String cep;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	
}
