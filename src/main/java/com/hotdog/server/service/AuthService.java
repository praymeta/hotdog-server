package com.hotdog.server.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotdog.server.web.dto.TokenResponseDTO;
import com.hotdog.server.web.dto.UserLoginFormDTO;
import com.hotdog.server.web.dto.UserRegisterFormDTO;
import com.hotdog.server.web.dto.UserRegisterResponseDTO;
import com.hotdog.server.exception.AuthException;
import com.hotdog.server.domain.user.User;
import com.hotdog.server.repository.UserRepository;
import com.hotdog.server.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public TokenResponseDTO login(UserLoginFormDTO userLoginFormDTO){

	User user = userRepository.findByEmail(userLoginFormDTO.getEmail())
		.orElseThrow(()-> new AuthException("사용자를 찾을 수 없습니다", HttpStatus.BAD_REQUEST));
	if(!userLoginFormDTO.getPassword().equals(user.getPassword()))
	{
		throw new AuthException("비밀번호가 일치하지 않습니다",HttpStatus.BAD_REQUEST);
	}
	String token = jwtTokenProvider.createToken(userLoginFormDTO);

		return new TokenResponseDTO(token, user.getId());


	}

	@Transactional
	public UserRegisterResponseDTO registerUser(UserRegisterFormDTO userRegisterFormDTO) {
		validateDupliateUser(userRegisterFormDTO);

		User user = User.builder()
			.email(userRegisterFormDTO.getEmail())
			.password(userRegisterFormDTO.getPassword())
			.username(userRegisterFormDTO.getUsername())
			.dog_breed(userRegisterFormDTO.getDog_breed())
			.picture(userRegisterFormDTO.getPicture())
			.build();

		User saveUser = userRepository.save(user);

		return new UserRegisterResponseDTO(saveUser.getId());

	}
	private void validateDupliateUser(UserRegisterFormDTO userRegisterFormDTO)
	{
		Optional<User> result = userRepository.findByEmail(userRegisterFormDTO.getEmail());
		if(result.isPresent())
		{
			throw new AuthException("이미 존재하는 사용자입니다",HttpStatus.BAD_REQUEST);
		}
	}
}
