package com.hotdog.server.service;

import org.springframework.stereotype.Service;

import com.hotdog.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

}
