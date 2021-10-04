package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ConfirmationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	private LocalDateTime localDateTime;
	
	private LocalDateTime expiredAt;
	
	private LocalDateTime confirmedAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}
	
	public ConfirmationToken() {
		
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public ConfirmationToken(String token, LocalDateTime localDateTime, LocalDateTime expiredAt, User user) {
		super();
		this.token = token;
		this.localDateTime = localDateTime;
		this.expiredAt = expiredAt;
		this.user = user;
	}
	
	
	
}
