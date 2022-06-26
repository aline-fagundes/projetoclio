package br.com.passaporteclio.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.passaporteclio.exception.handler.ExceptionHandlerFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private JwtProvider jwtProvider;

	public JwtConfigurer(JwtProvider tokenProvider) {
		this.jwtProvider = tokenProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtTokenFilter jwtFilter = new JwtTokenFilter(jwtProvider);
		ExceptionHandlerFilter handlerFilter = new ExceptionHandlerFilter(); 
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(handlerFilter, JwtTokenFilter.class);
	}
}