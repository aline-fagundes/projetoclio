package br.com.passaporteclio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Visitante;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
	
}
