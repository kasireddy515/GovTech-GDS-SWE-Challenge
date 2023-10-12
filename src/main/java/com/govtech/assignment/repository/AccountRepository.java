package com.govtech.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govtech.assignment.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	Account findByUserName(String userName);

}
