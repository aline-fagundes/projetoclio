package br.com.passaporteclio.controller;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.domain.entity.Avaliacao;
import br.com.passaporteclio.domain.vo.AvaliacaoVO;
import br.com.passaporteclio.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Avaliacao Endpoint")
@AllArgsConstructor
@RestController
// @RequestMapping("avaliacao")
public class AvaliacaoController {

	private AvaliacaoService avaliacaoService;

	@GetMapping(path = "/avaliacao", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Operation(summary = "Listar todas as avaliacao")
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

}
