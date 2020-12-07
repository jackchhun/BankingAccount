package com.mycompany.coding.sg.banking.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<OperationCD, Integer> {

	
	@Query("select o from OperationCD o where o.account.accountNumber = :accountNumber order by o.datetime asc")
	public Page<OperationCD> findByAccountNumberWithPaging(String accountNumber, Pageable pagingElements);
	
	public List<OperationCD> findAll();
	
}
