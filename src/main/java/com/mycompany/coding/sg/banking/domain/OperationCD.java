/**
 * 
 */
package com.mycompany.coding.sg.banking.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="operationcd")
public class OperationCD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = -1;

	@Column(name = "datetime", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime datetime = LocalDateTime.now();

	@Column(name="amount", nullable = false, columnDefinition="DECIMAL(10,2) default '0.00'")
	private Double amount = 0.0;

	@Column(name = "title", nullable = false, length = 128)
	private String title = "";

	@Column(name = "operation_type", nullable = false, length = 12)
	private String operationType = "";

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	@JsonIgnoreProperties("allOperations")
	@JsonIgnore
	private AccountCD account;


}
