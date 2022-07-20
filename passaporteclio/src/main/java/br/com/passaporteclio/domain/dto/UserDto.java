package br.com.passaporteclio.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDto extends RepresentationModel<UserDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
}
