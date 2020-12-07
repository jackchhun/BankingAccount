package com.mycompany.coding.sg.banking.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountCD, Integer> {

	public List<AccountCD> findAll();

	public AccountCD findByAccountNumber(String accountNumber);
	
}
