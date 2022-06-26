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
@Table(name="tb_endereco_museus")
public class Endereco implements Serializable {
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
	private Integer numero;
	
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_museu")
	@JsonBackReference
	private Museus museu;
}
