package br.com.passaporteclio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.AtualizaAvaliacaoDto;
import br.com.passaporteclio.domain.dto.AvaliacaoDto;
import br.com.passaporteclio.domain.dto.CriaAvaliacaoDto;
import br.com.passaporteclio.domain.dto.CriaAvaliacaoUserDto;
import br.com.passaporteclio.domain.entity.User;
import br.com.passaporteclio.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Avaliação Endpoint")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService service;

	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todas as avaliações ou apenas a do museu especificado")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AvaliacaoDto>> findAll(
			@RequestParam(required = false) Long idMuseu,
			@PageableDefault(sort = "nota", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {
		
		if (idMuseu == null) {
			Page<AvaliacaoDto> avaliacoesDto = service.buscarTodas(paginacao);
			avaliacoesDto.stream()
					.forEach(p -> p.add(linkTo(methodOn(AvaliacaoController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(avaliacoesDto));
		} else {
			Page<AvaliacaoDto> avaliacoesDto = service.buscarPorMuseu(idMuseu, paginacao);
			avaliacoesDto.stream()
					.forEach(p -> p.add(linkTo(methodOn(AvaliacaoController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(avaliacoesDto));
		}
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir avaliação por Id")
	@ResponseStatus(value = HttpStatus.OK)
	public AvaliacaoDto findById(@PathVariable("id") Long id) {
		
		AvaliacaoDto avaliacaoDto = service.buscarPorId(id);
		avaliacaoDto.add(linkTo(methodOn(AvaliacaoController.class).findById(id)).withSelfRel());
		return avaliacaoDto;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, 
			produces = { "application/json",
			"application/xml" })
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Cadastrar avaliação")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CriaAvaliacaoDto> create(@Valid @RequestBody CriaAvaliacaoDto avaliacao) {
		
		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();

		CriaAvaliacaoUserDto autor = new CriaAvaliacaoUserDto();
		autor.setId(idUsuarioLogado);
		avaliacao.setAutor(autor);
		avaliacao.setDenunciada(false);

		CriaAvaliacaoDto avaliacaoDto = service.inserir(avaliacao);
		return ResponseEntity.ok(avaliacaoDto);
	}

	@PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, 
			produces = { "application/json",
			"application/xml" })
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Alterar avaliação")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<AtualizaAvaliacaoDto> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody AtualizaAvaliacaoDto avaliacao) {
		
		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();

		AtualizaAvaliacaoDto avaliacaoDto = service.atualizar(id, avaliacao, idUsuarioLogado);
		return ResponseEntity.ok(avaliacaoDto);
	}

	@DeleteMapping(value = "/{id}")
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Deletar avaliação")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {

		User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuarioLogado = usuarioLogado.getId();
		String perfilUsuarioLogado = usuarioLogado.getPerfil();

		service.deletar(id, idUsuarioLogado, perfilUsuarioLogado);
	}

	@PostMapping(value = "denunciar/{id}")
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Denunciar avaliação")
	@ResponseStatus(value = HttpStatus.OK)
	public void report(@PathVariable("id") Long id) {

		service.denunciar(id);
	}

	@GetMapping(value = "/denuncias", produces = { "application/json", "application/xml" })
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Listar todas as avaliações denunciadas")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AvaliacaoDto>> findAllReported(
			@PageableDefault(sort = "nota", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {

		Page<AvaliacaoDto> avaliacoesDenunciadasDto = service.buscarAvaliacoesDenunciadas(paginacao);

		avaliacoesDenunciadasDto.stream()
				.forEach(p -> p.add(linkTo(methodOn(AvaliacaoController.class).findById(p.getId())).withSelfRel()));

		return ResponseEntity.ok(CollectionModel.of(avaliacoesDenunciadasDto));
	}
}
