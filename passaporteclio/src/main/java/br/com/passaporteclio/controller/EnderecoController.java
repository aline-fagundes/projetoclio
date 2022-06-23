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

import br.com.passaporteclio.domain.vo.EnderecoVO;
import br.com.passaporteclio.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endereco Endpoint")
@RestController
@RequestMapping("api/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService service;

	@CrossOrigin("localhost:8080")
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todos endereços")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<EnderecoVO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<EnderecoVO>enderecosVO = service.buscarTodos(pageable);
		enderecosVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(EnderecoController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(enderecosVO));
	}

	@CrossOrigin({ "localhost:8080"})
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public EnderecoVO findById(@PathVariable("id") Long id) {
		EnderecoVO enderecoVO = service.buscarPorId(id);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(id)).withSelfRel());
		return enderecoVO;
	}

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Listar endereco por CEP")
	@GetMapping(value = "/buscarPorCep/{cep}", produces = { "application/json", "application/xml" })
	public ResponseEntity<CollectionModel<EnderecoVO>> findAdressByCep(@PathVariable("cep") String cep,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "cep"));
		Page<EnderecoVO> enderecosVO = service.findByCep(cep, pageable);
		enderecosVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(EnderecoController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(enderecosVO));
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoVO create(@Valid @RequestBody EnderecoVO endereco) {
		EnderecoVO enderecoVO = service.inserir(endereco);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoVO.getKey())).withSelfRel());
		return enderecoVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public EnderecoVO update(@Valid @RequestBody EnderecoVO endereco) {
		EnderecoVO enderecoVO = service.atualizar(endereco);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoVO.getKey())).withSelfRel());
		return enderecoVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
}

