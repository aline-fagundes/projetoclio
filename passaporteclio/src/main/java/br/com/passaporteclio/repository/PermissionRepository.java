package br.com.passaporteclio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.passaporteclio.domain.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	Permission findFirstByPerfil(String perfil);

}
