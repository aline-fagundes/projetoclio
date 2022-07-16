package br.com.passaporteclio.exception.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import br.com.passaporteclio.exception.InvalidJwtAuthenticationException;

public class ExceptionHandlerFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (InvalidJwtAuthenticationException e) {
			((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("Token incorreto!");
		}
	}
}
