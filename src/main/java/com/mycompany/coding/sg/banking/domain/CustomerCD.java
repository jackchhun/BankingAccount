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
import javax.persistence.OneToMany;
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
@Table(name="customercd")
public class CustomerCD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = -1;

	@Column(name = "name", nullable = false, length = 32)
	private String name = "";
	
	@Column(name = "surname", nullable = false, length = 32)
	private String surname = "";
	
	@Column(name = "email", nullable = false, length = 64)
	private String email = "";

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	@JsonIgnoreProperties("customer")
	@JsonIgnore
	private List<AccountCD> allAccounts = new ArrayList<AccountCD>();

	
}
