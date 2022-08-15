package br.com.passaporteclio.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	private Long id;
	private String email;
	
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() { return id; }

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setId(Long id) { this.id = id; }

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}

