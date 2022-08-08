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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.MuseusDto;
import br.com.passaporteclio.domain.dto.NotaMediaMuseuDto;
import br.com.passaporteclio.service.MuseusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Museus Endpoint")
@RestController
@RequestMapping("/museus")
@SecurityRequirement(name = "bearer-key")
public class MuseusController {

	@Autowired
	MuseusService service;

	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todos os museus")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<MuseusDto>> findAll(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 9) Pageable paginacao) {
		
		Page<MuseusDto> museusDto = service.buscarTodos(paginacao);
		museusDto.stream()
				.forEach(p -> p.add(linkTo(methodOn(MuseusController.class).findById(p.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(museusDto));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir museu por Id")
	@ResponseStatus(value = HttpStatus.OK)
	public MuseusDto findById(@PathVariable("id") Long id) {
		
		MuseusDto museuDto = service.buscarPorId(id);
		museuDto.add(linkTo(methodOn(MuseusController.class).findById(id)).withSelfRel());
		return museuDto;
	}

	@GetMapping(value = "/buscarPorNome/{nome}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir museu por nome")
	@ResponseStatus(value = HttpStatus.OK)
	public MuseusDto findByNome(@PathVariable("nome") String nome) {

		MuseusDto museuDto = service.buscarPorNome(nome);
		museuDto.add(linkTo(methodOn(MuseusController.class).findByNome(nome)).withSelfRel());
		return museuDto;
	}
	
	@GetMapping(value = "/notaMedia/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir nota do museu por id")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<NotaMediaMuseuDto> getNotaMediaMuseu(@PathVariable("id") Long id) {
		
		NotaMediaMuseuDto notaMediaDto = service.calculaMedia(id);
		return ResponseEntity.ok(notaMediaDto);
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@Operation(summary = "Cadastrar museu")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<MuseusDto> create(@Valid @RequestBody MuseusDto museu) {
		
		MuseusDto museuDto = service.inserir(museu);
		return ResponseEntity.ok(museuDto);
	}

	@PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, 
			produces = { "application/json",
			"application/xml" })
	@Operation(summary = "Alterar museu")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<MuseusDto> update(
			@PathVariable("id") Long id, 
			@Valid @RequestBody MuseusDto museu) {
		
		MuseusDto museuDto = service.atualizar(id, museu);
		return ResponseEntity.ok(museuDto);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar museu")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		
		service.deletar(id);
	}
}
