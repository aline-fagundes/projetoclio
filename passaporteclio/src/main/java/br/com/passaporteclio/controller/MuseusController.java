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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import br.com.passaporteclio.domain.vo.MuseusVO;
import br.com.passaporteclio.service.MuseusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Museus Endpoint")
@RestController
@RequestMapping("/museus")
public class MuseusController {


		@Autowired
		MuseusService service;

		
		@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
		@Operation(summary = "Listar todos os museus")
		@ResponseStatus(value = HttpStatus.OK)
		public ResponseEntity<CollectionModel<MuseusVO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
				@RequestParam(value = "limit", defaultValue = "9") int limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction) {
			var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
			Page<MuseusVO> museusVO = service.buscarTodos(pageable);
			museusVO.stream()
					.forEach(p -> p.add(linkTo(methodOn(MuseusController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(museusVO));
		}

		
		@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
		@ResponseStatus(value = HttpStatus.OK)
		public MuseusVO findById(@PathVariable("id") Long id) {
			MuseusVO museuVO = service.buscarPorId(id);
			museuVO.add(linkTo(methodOn(MuseusController.class).findById(id)).withSelfRel());
			return museuVO;
		}

		
		@Operation(summary = "Listar museus por nome")
		@GetMapping(value = "/buscarPorNome/{nome}", produces = { "application/json", "application/xml" })
		public ResponseEntity<CollectionModel<MuseusVO>> findMuseumByName(@PathVariable("nome") String nome,
				@RequestParam(value = "page", defaultValue = "0") int page,
				@RequestParam(value = "limit", defaultValue = "10") int limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction) {
			var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
			Page<MuseusVO> museusVO = service.findByName(nome, pageable);
			museusVO.stream()
					.forEach(p -> p.add(linkTo(methodOn(MuseusController.class).findById(p.getId())).withSelfRel()));
			return ResponseEntity.ok(CollectionModel.of(museusVO));
		}

		@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
				"application/xml" })
		@ResponseStatus(value = HttpStatus.CREATED)
		public ResponseEntity<MuseusVO> create(@Valid @RequestBody MuseusVO museu) {
			MuseusVO museuVO = service.inserir(museu);
			return ResponseEntity.ok(museuVO);
		}

		@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
				"application/xml" })
		@ResponseStatus(value = HttpStatus.OK)
		public ResponseEntity<MuseusVO> update(@Valid @RequestBody MuseusVO museu) {
			MuseusVO museuVO = service.atualizar(museu);
			return ResponseEntity.ok(museuVO);
		}

		@DeleteMapping(value = "/{id}")
		@ResponseStatus(value = HttpStatus.OK)
		public void delete(@PathVariable("id") Long id) {
			service.delete(id);
		}
	}

