package br.com.passaporteclio.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mysql.cj.jdbc.Blob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_museus")
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
	
	
	@Lob
	@Column(name = "foto")
	private Blob fotoMuseu;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_endereco",nullable = true)
	private Endereco endereco;
	

}
