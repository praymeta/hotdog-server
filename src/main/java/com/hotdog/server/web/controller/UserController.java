package com.hotdog.server.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotdog.server.web.dto.TokenResponseDTO;
import com.hotdog.server.web.dto.UserLoginFormDTO;
import com.hotdog.server.web.dto.UserRegisterFormDTO;
import com.hotdog.server.web.dto.UserRegisterResponseDTO;
import com.hotdog.server.service.AuthService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final AuthService authService;

	@PostMapping("/auth/register")
	public UserRegisterResponseDTO registerUser(@RequestBody UserRegisterFormDTO userRegisterFormDTO) {
		System.out.println("ininin");
		return authService.registerUser(userRegisterFormDTO);
	}
	@PostMapping("/auth/login")
	public TokenResponseDTO login(@RequestBody UserLoginFormDTO userLoginFormDTO){
		return authService.login(userLoginFormDTO);
	}

	@GetMapping("denied")
	public String denied(){
		return "access denied";
	}
}
