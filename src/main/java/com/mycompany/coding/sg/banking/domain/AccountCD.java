/**
 * 
 */
package com.mycompany.coding.sg.banking.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="accountcd")
public class AccountCD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = -1;

	@Column(name = "name", nullable = false, length = 128)
	private String name = "";
	
	@Column(name = "account_number", nullable = false, unique = true, length = 16)
	private String accountNumber = "";
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	@JsonIgnoreProperties("account")
	@JsonIgnore
	private List<OperationCD> allOperations = new ArrayList<OperationCD>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnoreProperties("allAccounts")
	@JsonIgnore
	private CustomerCD customer;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
	private BalanceCD balance;

}
