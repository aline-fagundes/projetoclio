package br.com.passaporteclio.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="tb_avaliacao")
@NoArgsConstructor
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_avaliacao")
	private Long id;
	
	// @ManyToOne
	// private Usuario usuario;
	
	// fazer relacionamento na classe museu
	
	@NotNull
	@Column(name="nota")
	private int nota;
	
	@NotBlank
	@Size(max=300)
	@Column(name="avaliacao")
	private String avaliacao;

	
}
