package com.govtech.assignment.security.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.govtech.assignment.entity.Account;
import com.govtech.assignment.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -1979263265565211281L;

	private String id;
	private String username;
	private String email;
	private String type;
	private String userId;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String username, String email, String password, String type, String userId) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.userId = userId;
	}

	public static UserDetailsImpl build(Account account, User user) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = account.getPasswordHash() + "" + account.getPasswordSalt();
		String encodedPassword = passwordEncoder.encode(password);

		return new UserDetailsImpl(account.getId(), account.getUserName(), account.getUserName(), encodedPassword,
				"USER", user != null ? user.getId() : null);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
