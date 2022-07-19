package br.com.passaporteclio.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.passaporteclio.repository.UserRepository;
import br.com.passaporteclio.security.LoginForm;
import br.com.passaporteclio.security.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtProvider tokenProvider;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping(produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Operation(summary = "Logar com email e senha")
	public ResponseEntity<?> signin(@RequestBody LoginForm login) {

		try {
			var email = login.getEmail();
			var senha = login.getSenha();

			authManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

			var user = userRepository.findByEmail(email);
			var token = "";

			if(user != null) {
				token = tokenProvider.createToken(email, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Email " + email + "não localizado!");
			}
			Map<Object, Object> model = new HashMap<>();
			model.put("username", email);
			model.put("token", token);

			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Email ou senha incorretos!");
		}
	}
}