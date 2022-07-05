package br.com.passaporteclio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Teste")
@RestController
@RequestMapping("/api")
public class TestController {

	
	@GetMapping(value = "/testando", produces = { "application/json", "application/xml" })
	public String testando() {
		return "Ok!";
	}
}