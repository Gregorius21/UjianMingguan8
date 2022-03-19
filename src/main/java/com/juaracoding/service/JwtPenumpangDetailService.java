package com.juaracoding.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juaracoding.model.PenumpangModel;
import com.juaracoding.repository.PenumpangRepository;

@Service
public class JwtPenumpangDetailService implements UserDetailsService{

	@Autowired
	PenumpangRepository penumpangRepo;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PenumpangModel penumpang = penumpangRepo.findByUsername(username);
		return new User(penumpang.getUsername(), penumpang.getPassword(),
				new ArrayList<>());
	}
	

}
