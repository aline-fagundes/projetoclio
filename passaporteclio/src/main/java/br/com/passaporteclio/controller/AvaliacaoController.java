package br.com.passaporteclio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.vo.AtualizarAvaliavaoVO;
import br.com.passaporteclio.domain.vo.AvaliacaoVO;
import br.com.passaporteclio.domain.vo.CriacaoAvaliacaoVO;
import br.com.passaporteclio.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Avaliação Endpoint")
@AllArgsConstructor
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	private AvaliacaoService avaliacaoService;

	@GetMapping(produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Operation(summary = "Listar todas as avaliacoes")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AvaliacaoVO>> getAvaliacao(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "9") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nota"));
		Page<AvaliacaoVO> avaliacaoVO = avaliacaoService.getAll(pageable);
		avaliacaoVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(AvaliacaoController.class).findById(p.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(avaliacaoVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public AvaliacaoVO findById(@PathVariable("id") Long id) {
		AvaliacaoVO avaliacaoVO = avaliacaoService.getById(id);
		avaliacaoVO.add(linkTo(methodOn(AvaliacaoController.class).findById(id)).withSelfRel());
		return avaliacaoVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CriacaoAvaliacaoVO> create(@Valid @RequestBody CriacaoAvaliacaoVO avaliacao) {
		CriacaoAvaliacaoVO avaliacaoVO = avaliacaoService.inserir(avaliacao);
		return ResponseEntity.ok(avaliacaoVO);
	}

	@PutMapping(value = "/{id}", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<AtualizarAvaliavaoVO> update(@Valid @RequestBody AtualizarAvaliavaoVO avaliacao) {
		AtualizarAvaliavaoVO avaliacaoVO = avaliacaoService.atualizar(avaliacao);
		return ResponseEntity.ok(avaliacaoVO);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		avaliacaoService.delete(id);
	}

}
