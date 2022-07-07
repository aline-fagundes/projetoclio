package br.com.passaporteclio.domain.entity;

import com.mysql.cj.jdbc.Blob;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_museus")
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
	
	@Lob
	@Column(name = "foto")
	private Blob fotoMuseu;

	@OneToOne(mappedBy = "museu", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "museu", cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes;
}
