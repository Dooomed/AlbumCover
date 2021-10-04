package com.example.demo.service;

import java.time.LocalDateTime;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ConfirmationToken;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;

@Service
public class RegistrationService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	@Autowired
	private EmailServiceImpl emailService;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = EmailValidator.getInstance().isValid(request.getEmail());
		if(!isValidEmail) {
			throw new IllegalStateException("Email not valid!");
		}
		
		String tokenString =  userService.signUpUser(
				new User(request.getUsername(), request.getEmail(), request.getPassword(), Role.USER));
		
		emailService.send(request.getEmail(), tokenString);
		
		return "works!!!";
	}
	
	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("Token not found!"));
		if(confirmationToken.getConfirmedAt() != null) throw new IllegalStateException("Email already confirmed!");
		LocalDateTime expireDateTime = confirmationToken.getExpiredAt();
		if(expireDateTime.isBefore(LocalDateTime.now())) throw new IllegalStateException("Token is expired");
		
		confirmationTokenService.setConfirmedAt(token);
		userService.enableUser(confirmationToken.getUser().getEmail());
		return "confirmed";
	}
	
}
