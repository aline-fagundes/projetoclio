package br.com.passaporteclio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {

		var user = repository.findByEmail(email);

		if(user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Email " + email + " incorreto!");
		}
	}
}