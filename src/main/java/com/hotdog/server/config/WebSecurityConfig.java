package com.hotdog.server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hotdog.server.security.jwt.JwtAuthencationProvider;
import com.hotdog.server.security.jwt.JwtFillter;
import com.hotdog.server.security.jwt.JwtTokenProvider;
import com.hotdog.server.security.jwt.JwtTokenvalidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenvalidator jwtTokenvalidator;
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthencationProvider jwtAuthencationProvider;



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
			.disable()
			.httpBasic()
			.disable()

			.cors()
			.and()

			.csrf()
			.disable()

			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()

			.exceptionHandling()
			.and()

			.addFilterBefore(new JwtFillter(
				jwtTokenvalidator,
				jwtTokenProvider,
				jwtAuthencationProvider
			), UsernamePasswordAuthenticationFilter.class);
	}


}
