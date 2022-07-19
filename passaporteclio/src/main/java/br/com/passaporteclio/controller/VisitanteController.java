package br.com.passaporteclio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.VisitanteAlterarSenhaDto;
import br.com.passaporteclio.domain.dto.VisitanteAlterarDto;
import br.com.passaporteclio.domain.dto.VisitanteRetornoDto;
import br.com.passaporteclio.domain.dto.VisitanteDto;
import br.com.passaporteclio.service.VisitanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Visitante Endpoint")
@RestController
@RequestMapping("/visitante")
public class VisitanteController {
	@Autowired
	VisitanteService service;

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<VisitanteRetornoDto> create(@Valid @RequestBody VisitanteDto visitante) {
		VisitanteRetornoDto visitanteGravado = service.inserir(visitante);
		return ResponseEntity.ok(visitanteGravado);
	}

	@PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<VisitanteRetornoDto> update(@PathVariable("id") Long id,
			@Valid @RequestBody VisitanteAlterarDto visitante) {
		VisitanteRetornoDto visitanteAtualizado = service.atualizar(id, visitante);
		return ResponseEntity.ok(visitanteAtualizado);
	}

	@PutMapping(value = "/{id}/alterar-senha", consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<VisitanteRetornoDto> updatePassword(@PathVariable("id") Long id,
			@Valid @RequestBody VisitanteAlterarSenhaDto visitante) {
		VisitanteRetornoDto visitanteAtualizado = service.atualizarSenha(id, visitante);
		return ResponseEntity.ok(visitanteAtualizado);
	}
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todos os visitantes")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<VisitanteRetornoDto>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "9") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<VisitanteRetornoDto> visitanteAtualizado = service.buscarTodos(pageable);
		visitanteAtualizado.stream()
				.forEach(p -> p.add(linkTo(methodOn(VisitanteController.class).findById(p.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(visitanteAtualizado));
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Buscar visitante por id")
	@ResponseStatus(value = HttpStatus.OK)
	public VisitanteRetornoDto findById(@PathVariable("id") Long id) {
		VisitanteRetornoDto visitanteAtualizado = service.buscarPorId(id);
		visitanteAtualizado.add(linkTo(methodOn(VisitanteController.class).findById(id)).withSelfRel());
		return visitanteAtualizado;
	}
	

}
