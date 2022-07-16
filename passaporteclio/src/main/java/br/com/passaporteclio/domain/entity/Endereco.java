package br.com.passaporteclio.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table
public class Endereco implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_museu")
	@JsonBackReference
	private Museus museu;
}
