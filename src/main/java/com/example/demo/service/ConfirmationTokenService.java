package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ConfirmationToken;
import com.example.demo.repository.ConfirmationTokenRepository;

import antlr.Token;

@Service
public class ConfirmationTokenService {
	
	@Autowired
	private ConfirmationTokenRepository tokenRepository;
	
	public void saveToken(ConfirmationToken token) {
		tokenRepository.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token) {
		return this.tokenRepository.findByToken(token);
	}
	
	public void setConfirmedAt(String token) {
		ConfirmationToken confirmationToken = getToken(token).orElseThrow(() -> new IllegalStateException("Token not found!!!"));
		confirmationToken.setConfirmedAt(LocalDateTime.now());
		this.tokenRepository.updateConfirmedAtToken(token, LocalDateTime.now());
	}
	

}
