package com.govtech.assignment.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.govtech.assignment.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

	User findByAccountId(String accountId);

	Page<User> findAll(Pageable paging);

	Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName,
			Pageable paging);

	Long countByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

	Set<User> findByIdIn(Set<String> userIds);

}
