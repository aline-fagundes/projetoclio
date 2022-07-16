package br.com.passaporteclio.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table
@Data
@NoArgsConstructor
public class Museus implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_museu")
	private Long id;
	
	@NotBlank
	@Size(max=100)
	@Column(name = "nome_museu")
	private String nome;
	
	@NotBlank
	@Size(max=1000)
	@Column(name = "descricao")
	private String descricaoMuseu;
	
	@NotBlank
	@Size(max=500)
	@Column(name = "funcionamento")
	private String funcionamentoMuseu;
	
	@NotBlank
	@Column(name = "foto")
	private String urlFoto;

	@OneToOne(mappedBy = "museu", cascade = CascadeType.ALL)
	private Endereco endereco;
}
