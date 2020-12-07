/**
 * 
 */
package com.mycompany.coding.sg.banking.view;

import java.time.LocalDateTime;

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
public class BalanceView {

	private String accountNumber = "";
	
	private LocalDateTime datetime = LocalDateTime.now();
	
	private Double balance = 0.0;
	
}
