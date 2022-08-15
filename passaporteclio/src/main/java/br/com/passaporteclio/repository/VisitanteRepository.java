package br.com.passaporteclio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Visitante;

import java.util.Optional;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    Optional<Visitante> findFistByUserId(Long userId);
}
