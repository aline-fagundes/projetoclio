package br.com.passaporteclio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Museus;

@Repository
public interface MuseusRepository extends JpaRepository<Museus, Long> {
	
	Page<Museus> findByNome(String nome, Pageable pageable);
	
	@Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.museu.id = :id_museu")
	Double getNotaMedia(@Param("id_museu") Long idMuseu);
	
}
