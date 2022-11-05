package com.hotdog.server.security.jwt;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFillter extends OncePerRequestFilter {
	private final JwtTokenvalidator jwtTokenvalidator;
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthencationProvider jwtAuthencationProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		final String token = parseBearerToken(request);

		if(token == null){
			logger.warn("JWT Token does not begin with Bearer String");
			filterChain.doFilter(request,response);
			return;
		}

		try {
			String email = jwtTokenProvider.validateAndGetEmail(token);//토큰 검증

			if(jwtTokenvalidator.validateToken(token))
			{

				Authentication authentication = JwtAuthentication.of(null, email);

				Authentication authenticated = jwtAuthencationProvider.authenticate(authentication);

				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authenticated);
				SecurityContextHolder.setContext(securityContext);
			}

		} catch (IllegalStateException e){
			logger.error("Unable to fetch JWT Token");
		} catch (ExpiredJwtException e){
			logger.error("JWT Token has expired");
		} catch (Exception e) {
			logger.error("Could not set user authentication in security context",e);
		}

		filterChain.doFilter(request,response);
	}


	private String parseBearerToken(HttpServletRequest request){
		String bearerToken = request.getHeader("Authorization");

		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
			return bearerToken.substring(7);
		}
		return null;
	}
}
