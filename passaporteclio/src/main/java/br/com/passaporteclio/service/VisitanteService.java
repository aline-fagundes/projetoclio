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
import br.com.passaporteclio.domain.dto.VisitanteAlterarDto;
import br.com.passaporteclio.domain.dto.VisitanteAlterarSenhaDto;
import br.com.passaporteclio.domain.dto.VisitanteDto;
import br.com.passaporteclio.domain.dto.VisitanteRetornoDto;
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
	
	public VisitanteRetornoDto inserir(VisitanteDto visitanteDTO) {
		System.out.println("Iniciando método inserir...");
		
		if(userRepository.findByEmail(visitanteDTO.getUser().getEmail()) != null){
			throw new IllegalArgumentException("Ja existe um usuario cadastrado com o e-mail informado!");
		}

		var visitanteEntity = DozerConverter.parseObject(visitanteDTO, Visitante.class);

		var userEntity = visitanteEntity.getUser();
		userEntity.setSenha(new BCryptPasswordEncoder().encode(userEntity.getSenha()));
		userEntity.setPerfil(PERFIL_VISITANTE);
		userEntity.setPermissions(getPermissions());
		
		var userGravado = userRepository.save(userEntity);
		
		visitanteEntity.setUser(userGravado);

		var visitanteGravado = DozerConverter.parseObject(visitanteRepository.save(visitanteEntity), VisitanteRetornoDto.class);

		System.out.println("Finalizando método inserir...");
		return visitanteGravado;
	}
	
	public VisitanteRetornoDto atualizar(Long id, VisitanteAlterarDto visitanteAlterarDTO) {
		System.out.println("Iniciando método atualizar...");
		
		var entityVisitante = visitanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));

		entityVisitante.setNome(visitanteAlterarDTO.getNome());
		entityVisitante.setSobrenome(visitanteAlterarDTO.getSobrenome());

		var visitanteGravado = DozerConverter.parseObject(
				visitanteRepository.save(entityVisitante), VisitanteRetornoDto.class
		);

		System.out.println("Finalizando método atualizar...");
		return visitanteGravado;
	}
	
	public VisitanteRetornoDto atualizarSenha(Long id, VisitanteAlterarSenhaDto visitanteAlterarSenhaDTO) {
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
	

		var visitanteGravado = DozerConverter.parseObject(entityVisitante, VisitanteRetornoDto.class);

		System.out.println("Finalizando método atualizarSenha...");
		return visitanteGravado;
	}

	public Page<VisitanteRetornoDto> buscarTodos(Pageable pageable) {
		var page = visitanteRepository.findAll(pageable);
		return page.map(this::convertToVisitanteDTO);
	}

	public VisitanteRetornoDto buscarPorId(Long id) {
		var entity = visitanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		return DozerConverter.parseObject(entity, VisitanteRetornoDto.class);
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
	
	private VisitanteRetornoDto convertToVisitanteDTO(Visitante entity) {
		return DozerConverter.parseObject(entity, VisitanteRetornoDto.class);
	}
}
