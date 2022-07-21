package br.com.passaporteclio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.dto.AlteraSenhaVisitanteDto;
import br.com.passaporteclio.domain.dto.AlteraVisitanteDto;
import br.com.passaporteclio.domain.dto.CriaVisitanteDto;
import br.com.passaporteclio.domain.dto.VisitanteDto;
import br.com.passaporteclio.domain.entity.Permission;
import br.com.passaporteclio.domain.entity.Visitante;
import br.com.passaporteclio.exception.OperationNotAllowedException;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.PermissionRepository;
import br.com.passaporteclio.repository.UserRepository;
import br.com.passaporteclio.repository.VisitanteRepository;

@Service
public class VisitanteService {

	@Autowired
	VisitanteRepository repository; 
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private String PERFIL_VISITANTE = "Visitante";
	private String ROLE_VISITANTE = "ROLE_VISITANTE";
	
	public CriaVisitanteDto inserir(VisitanteDto visitanteDTO) {
		System.out.println("Iniciando método inserir...");
		
		if(userRepository.findByEmail(visitanteDTO.getUser().getEmail()) != null){
			throw new IllegalArgumentException("Já existe cadastro com o e-mail informado!");
		}

		var visitanteEntity = DozerConverter.parseObject(visitanteDTO, Visitante.class);

		var userEntity = visitanteEntity.getUser();
		userEntity.setSenha(new BCryptPasswordEncoder().encode(userEntity.getSenha()));
		userEntity.setPerfil(PERFIL_VISITANTE);
		userEntity.setPermissions(getPermissions());
		
		var userGravado = userRepository.save(userEntity);
		
		visitanteEntity.setUser(userGravado);

		var visitanteGravado = DozerConverter.parseObject(repository.save(visitanteEntity), CriaVisitanteDto.class);

		System.out.println("Finalizando método inserir...");
		return visitanteGravado;
	}
	
	
	public CriaVisitanteDto atualizar(Long id, AlteraVisitanteDto visitanteAlterarDTO, Long idUsuarioLogado) {
		System.out.println("Iniciando método atualizar...");
		
		var entityVisitante = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));

		if(idUsuarioLogado != entityVisitante.getUser().getId()) {
			throw new OperationNotAllowedException("Não é possível alterar o perfil de outro visitante!");
		}
		
		entityVisitante.setNome(visitanteAlterarDTO.getNome());
		entityVisitante.setSobrenome(visitanteAlterarDTO.getSobrenome());

		var visitanteGravado = DozerConverter.parseObject(
				repository.save(entityVisitante), CriaVisitanteDto.class
		);

		System.out.println("Finalizando método atualizar...");
		return visitanteGravado;
	}
	
	
	public CriaVisitanteDto atualizarSenha(Long id, AlteraSenhaVisitanteDto visitanteAlterarSenhaDTO, Long idUsuarioLogado) {
		System.out.println("Iniciando método atualizar senha...");
		
		var passwordEncoder = new BCryptPasswordEncoder();
		
		var entityVisitante = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		if(idUsuarioLogado != entityVisitante.getUser().getId()) {
			throw new OperationNotAllowedException("Não é possível alterar a senha de outro visitante!");
		}
		
		if(!passwordEncoder.matches(visitanteAlterarSenhaDTO.getSenhaAntiga(), entityVisitante.getUser().getSenha())) {
			throw new IllegalArgumentException("Senha antiga informada não confere!");
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
	

		var visitanteGravado = DozerConverter.parseObject(entityVisitante, CriaVisitanteDto.class);

		System.out.println("Finalizando método atualizar senha...");
		return visitanteGravado;
	}

	
	public Page<CriaVisitanteDto> buscarTodos(Pageable paginacao) {
		
		var page = repository.findAll(paginacao);
		return page.map(this::convertToVisitanteDTO);
	}

	
	public CriaVisitanteDto buscarPorId(Long id, Long idUsuarioLogado, String perfilUsuarioLogado) {
			
		var entityVisitante = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
	
		Long idUrl = entityVisitante.getUser().getId();
		
		if(!perfilUsuarioLogado.equals("Administrador") && idUsuarioLogado.equals(idUrl)) {
			throw new OperationNotAllowedException("Não é possível consultar perfil de outro visitante!");
		}
		
		return DozerConverter.parseObject(entityVisitante, CriaVisitanteDto.class);
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
	
	
	private CriaVisitanteDto convertToVisitanteDTO(Visitante visitanteEntity) {
		
		return DozerConverter.parseObject(visitanteEntity, CriaVisitanteDto.class);
	}
}
