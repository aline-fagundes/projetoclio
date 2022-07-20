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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.AlteraVisitanteDto;
import br.com.passaporteclio.domain.dto.AlteraSenhaVisitanteDto;
import br.com.passaporteclio.domain.dto.VisitanteDto;
import br.com.passaporteclio.domain.dto.CriaVisitanteDto;
import br.com.passaporteclio.service.VisitanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Visitante Endpoint")
@RestController
@RequestMapping("/visitante")
public class VisitanteController {
	
	@Autowired
	VisitanteService service;

	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todos os visitantes")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<CriaVisitanteDto>> findAll(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {
		
		Page<CriaVisitanteDto> visitanteAtualizado = service.buscarTodos(paginacao);
		visitanteAtualizado.stream()
				.forEach(p -> p.add(linkTo(methodOn(VisitanteController.class).findById(p.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(visitanteAtualizado));
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir visitante por id")
	@ResponseStatus(value = HttpStatus.OK)
	public CriaVisitanteDto findById(@PathVariable("id") Long id) {
		
		CriaVisitanteDto visitanteAtualizado = service.buscarPorId(id);
		visitanteAtualizado.add(linkTo(methodOn(VisitanteController.class).findById(id)).withSelfRel());
		return visitanteAtualizado;
	}
	
	@PostMapping(consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@Operation(summary = "Cadastrar visitante")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CriaVisitanteDto> create(@Valid @RequestBody VisitanteDto visitante) {
		
		CriaVisitanteDto visitanteGravado = service.inserir(visitante);
		return ResponseEntity.ok(visitanteGravado);
	}

	@PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CriaVisitanteDto> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody AlteraVisitanteDto visitante) {
		
		CriaVisitanteDto visitanteAtualizado = service.atualizar(id, visitante);
		return ResponseEntity.ok(visitanteAtualizado);
	}

	@PutMapping(value = "/alterar-senha/{id}", consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CriaVisitanteDto> updatePassword(
			@PathVariable("id") Long id,
			@Valid @RequestBody AlteraSenhaVisitanteDto visitante) {
		
		CriaVisitanteDto visitanteAtualizado = service.atualizarSenha(id, visitante);
		return ResponseEntity.ok(visitanteAtualizado);
	}
}
