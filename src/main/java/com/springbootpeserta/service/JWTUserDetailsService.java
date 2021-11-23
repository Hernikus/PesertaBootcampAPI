package com.springbootpeserta.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootpeserta.model.Peserta;
import com.springbootpeserta.repository.PesertaRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {
	@Autowired
	PesertaRepository pesertaRepo;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Peserta user = pesertaRepo.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), new	ArrayList<>());
	}
}
