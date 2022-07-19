package br.com.passaporteclio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Presenca;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
	
	Page<Presenca> findByMuseuId(Long museuId, Pageable pageable);

}
