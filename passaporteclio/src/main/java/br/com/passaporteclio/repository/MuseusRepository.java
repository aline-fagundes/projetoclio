package br.com.passaporteclio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Museus;

@Repository
public interface MuseusRepository extends JpaRepository<Museus, Long> {
	
	Page<Museus> findByNome(String nome,Pageable pageable);
	
}
