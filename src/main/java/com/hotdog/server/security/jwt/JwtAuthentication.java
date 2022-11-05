package com.hotdog.server.security.jwt;

import java.util.Collection;

import javax.security.auth.Subject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthentication implements Authentication {

	private final UserDetailsAdapter userDetailsAdapter;
	private final String email;

	public static Authentication of(UserDetailsAdapter userDetailsAdapter, String email) {
		return new JwtAuthentication(userDetailsAdapter, email);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean implies(Subject subject) {
		return Authentication.super.implies(subject);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}
}
