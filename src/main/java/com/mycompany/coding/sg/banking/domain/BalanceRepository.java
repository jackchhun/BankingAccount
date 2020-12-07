package com.mycompany.coding.sg.banking.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceCD, Integer> {

}
