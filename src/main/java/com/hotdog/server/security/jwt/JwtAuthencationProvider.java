package com.hotdog.server.security.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthencationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
		return JwtAuthentication.of((UserDetailsAdapter)userDetails, userDetails.getUsername());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
