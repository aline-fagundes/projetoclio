package br.com.passaporteclio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.CriaPresencaDto;
import br.com.passaporteclio.domain.dto.CriaPresencaUserDto;
import br.com.passaporteclio.domain.dto.PresencaDto;
import br.com.passaporteclio.domain.entity.User;
import br.com.passaporteclio.service.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Presença Endpoint")
@RestController
@RequestMapping("/presenca")
@SecurityRequirement(name = "bearer-key")
public class PresencaController {

	@Autowired
	private PresencaService service;

	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todas as presenças ou apenas a do museu especificado")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<PresencaDto>> findAll(
			@RequestParam(required = false) Long idMuseu,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {

		if (idMuseu == null) {
			Page<PresencaDto> presencasDto = service.buscarTodas(paginacao);
			presencasDto.stream().forEach(
					p -> p.add(linkTo(methodOn(PresencaController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(presencasDto));
		} else {
			Page<PresencaDto> presencasDto = service.buscarPorMuseu(idMuseu, paginacao);
			presencasDto.stream().forEach(
					p -> p.add(linkTo(methodOn(PresencaController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(presencasDto));
		}
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir presença por Id")
	@ResponseStatus(value = HttpStatus.OK)
	public PresencaDto findById(@PathVariable("id") Long id) {
		
		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();
		String perfilUsuarioLogado = usuarioLogado.getPerfil();
		
		PresencaDto presencaDto = service.buscarPorId(id, idUsuarioLogado, perfilUsuarioLogado);
		presencaDto.add(linkTo(methodOn(PresencaController.class).findById(id)).withSelfRel());
		return presencaDto;
	}
	
	@GetMapping(value = "doVisitante/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir presenças de um visitante através de seu User-Id")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<PresencaDto>> findByIdVisitante(
			@PathVariable("id") Long id,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {
		
		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();
		String perfilUsuarioLogado = usuarioLogado.getPerfil();
		
		Page<PresencaDto> presencasDto = service.buscarPorVisitante(id, idUsuarioLogado, perfilUsuarioLogado, paginacao);
		return ResponseEntity.ok(CollectionModel.of(presencasDto));
	}
	
	@PostMapping(consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@Operation(summary = "Cadastrar presença")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CriaPresencaDto> create(@Valid @RequestBody CriaPresencaDto presenca) {

		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();

		CriaPresencaUserDto autor = new CriaPresencaUserDto();
		autor.setId(idUsuarioLogado);
		presenca.setAutor(autor);
		presenca.setData(LocalDateTime.now());
		
		CriaPresencaDto presencaDto = service.inserir(presenca);
		return ResponseEntity.ok(presencaDto);
	}
}
