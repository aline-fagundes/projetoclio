package br.com.passaporteclio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.passaporteclio.security.jwt.JwtConfigurer;
import br.com.passaporteclio.security.jwt.JwtProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
        .antMatchers(HttpMethod.GET, "/museus").authenticated()
        .antMatchers(HttpMethod.GET, "/museus/notaMedia/*").authenticated()
        .antMatchers(HttpMethod.POST, "/museus").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.PUT, "/museus/*").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.DELETE, "/museus/*").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.POST, "/visitante").permitAll()
        .antMatchers(HttpMethod.PUT, "/visitante/*").authenticated()
        .antMatchers(HttpMethod.PUT, "/visitante/alterar-senha/*").authenticated()
        .antMatchers(HttpMethod.GET, "/visitante").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.GET, "/visitante/*").authenticated()
        .antMatchers(HttpMethod.GET, "/avaliacao").authenticated()
        .antMatchers(HttpMethod.GET, "/avaliacao/denuncias").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.GET, "/avaliacao/*").authenticated()
        .antMatchers(HttpMethod.POST, "/avaliacao").authenticated()
        .antMatchers(HttpMethod.PUT, "/avaliacao").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.PUT, "/avaliacao/*").authenticated()
        .antMatchers(HttpMethod.POST, "/avaliacao/denunciar/*").authenticated()
        .antMatchers(HttpMethod.DELETE, "/avaliacao/*").authenticated()
        .antMatchers(HttpMethod.GET, "/presenca").hasRole("ADMINISTRADOR")
        .antMatchers(HttpMethod.GET, "/presenca/*").authenticated()
        .antMatchers(HttpMethod.GET, "/presenca/doVisitante/*").authenticated()
        .antMatchers(HttpMethod.POST, "/presenca").authenticated()       
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().apply(new JwtConfigurer(jwtProvider));
	}
	
	@Override 
	public void configure(WebSecurity web) throws Exception { 
	    web.ignoring() 
	        .antMatchers("/swagger-ui/**", "/v3/api-docs/**"); 
	}
}