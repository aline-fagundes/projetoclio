package br.com.passaporteclio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Permission;
import br.com.passaporteclio.domain.entity.Visitante;
import br.com.passaporteclio.domain.vo.VisitanteAlterarDTO;
import br.com.passaporteclio.domain.vo.VisitanteAlterarSenhaDTO;
import br.com.passaporteclio.domain.vo.VisitanteDTO;
import br.com.passaporteclio.domain.vo.VisitanteRetornoDTO;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.PermissionRepository;
import br.com.passaporteclio.repository.UserRepository;
import br.com.passaporteclio.repository.VisitanteRepository;

@Service
public class VisitanteService {

	@Autowired
	VisitanteRepository visitanteRepository; 
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private String PERFIL_VISITANTE = "Visitante";
	private String ROLE_VISITANTE = "ROLE_VISITANTE";
	
	public VisitanteRetornoDTO inserir(VisitanteDTO visitanteDTO) {
		System.out.println("Iniciando método inserir...");

		var visitanteEntity = DozerConverter.parseObject(visitanteDTO, Visitante.class);

		var userEntity = visitanteEntity.getUser();
		userEntity.setSenha(new BCryptPasswordEncoder().encode(userEntity.getSenha()));
		userEntity.setPerfil(PERFIL_VISITANTE);
		userEntity.setPermissions(getPermissions());
		
		var userGravado = userRepository.save(userEntity);
		
		visitanteEntity.setUser(userGravado);

		var visitanteGravado = DozerConverter.parseObject(visitanteRepository.save(visitanteEntity), VisitanteRetornoDTO.class);

		System.out.println("Finalizando método inserir...");
		return visitanteGravado;
	}
	
	public VisitanteRetornoDTO atualizar(Long id, VisitanteAlterarDTO visitanteAlterarDTO) {
		System.out.println("Iniciando método atualizar...");
		
		var entityVisitante = visitanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));

		entityVisitante.setNome(visitanteAlterarDTO.getNome());
		entityVisitante.setSobrenome(visitanteAlterarDTO.getSobrenome());

		var visitanteGravado = DozerConverter.parseObject(
				visitanteRepository.save(entityVisitante), VisitanteRetornoDTO.class
		);

		System.out.println("Finalizando método atualizar...");
		return visitanteGravado;
	}
	
	public VisitanteRetornoDTO atualizarSenha(Long id, VisitanteAlterarSenhaDTO visitanteAlterarSenhaDTO) {
		System.out.println("Iniciando método atualizarSenha...");
		
		var passwordEncoder = new BCryptPasswordEncoder();
		
		var entityVisitante = visitanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		if(!passwordEncoder.matches(visitanteAlterarSenhaDTO.getSenhaAntiga(), entityVisitante.getUser().getSenha())) {
			throw new IllegalArgumentException("Senha antiga informada não confere");
		}
		
		if(!visitanteAlterarSenhaDTO.getSenhaNova().equals(visitanteAlterarSenhaDTO.getConfirmaSenhaNova())) {
			throw new IllegalArgumentException("Senha nova não confere com confirmação de senha!");
		}
		
		if(passwordEncoder.matches(visitanteAlterarSenhaDTO.getSenhaNova(), entityVisitante.getUser().getSenha())) {
			throw new IllegalArgumentException("Senha nova não pode ser igual a senha antiga!");
		}
		
		var userEntity = entityVisitante.getUser();
		userEntity.setSenha(passwordEncoder.encode(visitanteAlterarSenhaDTO.getSenhaNova()));

		userRepository.save(userEntity);
	

		var visitanteGravado = DozerConverter.parseObject(entityVisitante, VisitanteRetornoDTO.class);

		System.out.println("Finalizando método atualizarSenha...");
		return visitanteGravado;
	}

	public Page<VisitanteRetornoDTO> buscarTodos(Pageable pageable) {
		var page = visitanteRepository.findAll(pageable);
		return page.map(this::convertToVisitanteDTO);
	}

	public VisitanteRetornoDTO buscarPorId(Long id) {
		var entity = visitanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		return DozerConverter.parseObject(entity, VisitanteRetornoDTO.class);
	}
	

	private List<Permission> getPermissions(){
		var permissaoVisitante = permissionRepository.findFirstByPerfil(ROLE_VISITANTE);
		
		if(permissaoVisitante == null) {
			throw new ResourceNotFoundException("Não foi encontrado a role de visitante!");
		}
		
		var list = new ArrayList<Permission>();
		list.add(permissaoVisitante);
		return list;
	}
	
	private VisitanteRetornoDTO convertToVisitanteDTO(Visitante entity) {
		return DozerConverter.parseObject(entity, VisitanteRetornoDTO.class);
	}
}
