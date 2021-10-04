package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.ConfirmationToken;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ConfirmationTokenService tokenService;
	
	
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
	public User findUserById(Long id) {
		return this.userRepository.getById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with " + email + "not found."));
	}

	
	public String signUpUser(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
		if(userExists) throw new IllegalStateException("Email already taken");
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
		);
		
		tokenService.saveToken(confirmationToken);
		
		// TODO: SEND EMAIL
		
		userRepository.save(user);
		return token;
	}
	
	public void enableUser(String email) {
		this.userRepository.enableUser(email);
	}
}
