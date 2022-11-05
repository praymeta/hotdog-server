package com.hotdog.server.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Tables;

import com.hotdog.server.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "dog_breed")
	private String dog_breed;

	@Column(name = "picture", columnDefinition = "longtext")
	private String picture;

	@Enumerated
	@Column(name = "authority")
	private Authority authority;

	@Builder
	public User(String email, String password, String username, String dog_breed, String picture) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.dog_breed = dog_breed;
		this.picture = picture;
		this.authority = Authority.USER;
	}

}
