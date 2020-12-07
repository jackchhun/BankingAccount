/**
 * 
 */
package com.mycompany.coding.sg.banking.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kimchhun2
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="balancecd")
public class BalanceCD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = -1;

	@Column(name = "datetime", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime datetime = LocalDateTime.now();

	@Column(name="balance", nullable = false, columnDefinition="DECIMAL(10,2) default '0.00'")
	private Double balance = 0.0;

	@OneToOne(mappedBy = "balance")
	private AccountCD account;
	
}
