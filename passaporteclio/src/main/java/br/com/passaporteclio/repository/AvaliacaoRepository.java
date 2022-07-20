package br.com.passaporteclio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	Page<Avaliacao> findByMuseuId(Long idMuseu, Pageable pageable);

	Page<Avaliacao> findByDenunciada(boolean denunciada, Pageable pageable);
	
}
