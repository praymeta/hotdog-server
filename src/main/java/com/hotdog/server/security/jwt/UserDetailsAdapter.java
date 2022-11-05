package com.hotdog.server.security.jwt;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotdog.server.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDetailsAdapter implements UserDetails{
	private User user;

	public static UserDetails from(User user){
		return new UserDetailsAdapter(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(()->user.getAuthority().getRole());
	}

	@Override
	public  String getPassword(){
		return null;
	}
	@Override
	public String getUsername(){
		return user.getEmail();
	}
	@Override
	public boolean isAccountNonExpired(){
		return false;
	}
	@Override
	public boolean isAccountNonLocked(){
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired(){
		return false;
	}
	@Override
	public boolean isEnabled(){
		return true;
	}

}
