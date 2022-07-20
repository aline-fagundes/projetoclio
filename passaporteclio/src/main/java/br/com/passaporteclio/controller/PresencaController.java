package br.com.passaporteclio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.dto.CriacaoPresencaDto;
import br.com.passaporteclio.domain.dto.CriacaoPresencaMuseuDto;
import br.com.passaporteclio.domain.dto.PresencaDto;
import br.com.passaporteclio.service.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Presença Endpoint")
@RestController
@RequestMapping("/presenca")
@AllArgsConstructor
public class PresencaController {

	private PresencaService presencaService;

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir presença por Id")
	@ResponseStatus(value = HttpStatus.OK)
	public PresencaDto buscarPorId(@PathVariable("id") Long id) {
		PresencaDto presencaDto = presencaService.buscarPorId(id);
		presencaDto.add(linkTo(methodOn(PresencaController.class).buscarPorId(id)).withSelfRel());
		return presencaDto;
	}

	@GetMapping(value = "", produces = { "application/json", "application/xml" })
	@Operation(summary = "Exibir todas as presenças ou apenas a do museu especificado")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<PresencaDto>> buscarTodos(@RequestParam(required = false) Long museuId,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {

		if (museuId == null) {
			var page = presencaService.buscarTodos(paginacao);
			page.stream().forEach(
					p -> p.add(linkTo(methodOn(PresencaController.class).buscarPorId(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(page));
		} else {
			var page = presencaService.buscarPorMuseuId(museuId, paginacao);
			page.stream().forEach(
					p -> p.add(linkTo(methodOn(PresencaController.class).buscarPorId(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(page));
		}

	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@SecurityRequirement(name = "bearer-key")
	@Operation(summary = "Cadastrar presença")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CriacaoPresencaDto> create(@Valid @RequestBody CriacaoPresencaDto presenca) {

		presenca.setData(LocalDateTime.now());
		CriacaoPresencaDto presencaDto = presencaService.inserir(presenca);
		return ResponseEntity.ok(presencaDto);
	}

}
