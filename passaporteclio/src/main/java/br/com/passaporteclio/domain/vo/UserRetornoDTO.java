package br.com.passaporteclio.domain.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserRetornoDTO extends RepresentationModel<UserRetornoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String email;
}
