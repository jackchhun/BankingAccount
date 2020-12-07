/**
 * 
 */
package com.mycompany.coding.sg.banking.web;

import org.springframework.data.domain.Page;

import com.mycompany.coding.sg.banking.view.BalanceView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankResponse {

	private String returnCode = "";
	
	private String returnMessage = "";
	
	private String serviceCalled = "";
	
	@SuppressWarnings("rawtypes")
	private Page history = null;
	
	private BalanceView balance = null;
	
}
