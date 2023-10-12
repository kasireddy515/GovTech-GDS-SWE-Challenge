package com.govtech.assignment.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.govtech.assignment.entity.Account;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.repository.AccountRepository;
import com.govtech.assignment.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;
	private final UserRepository repository;

	UserDetailsServiceImpl(AccountRepository accountRepository, UserRepository repository) {
		this.accountRepository = accountRepository;
		this.repository = repository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserName(username);
		if (account == null) {
			new UsernameNotFoundException("User Not Found with username: " + username);
		}
		User user = repository.findByAccountId(account.getId());
		return UserDetailsImpl.build(account, user);
	}

}
