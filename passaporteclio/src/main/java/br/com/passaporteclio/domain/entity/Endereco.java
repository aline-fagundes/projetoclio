package br.com.passaporteclio.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_endereco_museus")
@Data
@NoArgsConstructor
public class Endereco implements Serializable {
	 
	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
	private Long id;
	
	@NotBlank
	@Size(max=8)
	@Column(name="cep")
	private String cep;
	
	@NotBlank
	@Size(max=45)
	@Column(name="rua")
	private String rua;
	
	
	@NotNull
	@Column(name="numero")
	private int numero;
	
	@NotBlank
	@Size(max=45)
	@Column(name="bairro")
	private String bairro;
	
	@NotBlank
	@Size(max=45)
	@Column(name="cidade")
	private String cidade;
	
	@NotBlank
	@Size(max=2)
	@Column(name="estado")
	private String estado;
	
	@NotBlank
	@Size(max=45)
	@Column(name="pais")
	private String pais;
	

}
