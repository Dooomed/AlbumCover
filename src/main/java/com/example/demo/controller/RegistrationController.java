package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.RegistrationService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationService registrationService;

	
	@PostMapping()
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
	@GetMapping("/confirm")
	public String confirmToken(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
}
