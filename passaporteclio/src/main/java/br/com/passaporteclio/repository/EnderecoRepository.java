package br.com.passaporteclio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.passaporteclio.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	Page<Endereco> findByCep(String cep,Pageable pageable);
	
	

}
