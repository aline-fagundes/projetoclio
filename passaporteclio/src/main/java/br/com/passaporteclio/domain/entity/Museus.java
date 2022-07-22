package br.com.passaporteclio.domain.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Museus implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
	
	@Column(name = "site")
	private String urlSite;
	
	@NotBlank
	@Column(name = "instagram")
	private String urlInstagram;
	
	@OneToOne(mappedBy = "museu", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "museu", cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes = new ArrayList();

	@OneToMany(mappedBy = "museu", cascade = CascadeType.ALL)
	private List<Presenca> presencas = new ArrayList();
}
