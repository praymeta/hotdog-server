package com.hotdog.server.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

	private Long id;

	private String username;

	private String email;

	private String dog_breed;

	private String picture;

}
