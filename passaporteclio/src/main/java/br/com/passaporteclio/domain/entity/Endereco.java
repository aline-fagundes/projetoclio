package br.com.passaporteclio.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

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
	@JoinColumn(name = "museu_id_endereco")
	@JsonBackReference
	private Museus museu;
}
