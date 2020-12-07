package com.mycompany.coding.sg.banking.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerCD, Integer> {

	public List<CustomerCD> findAll();
	
}
