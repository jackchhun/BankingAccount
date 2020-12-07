/**
 * 
 */
package com.mycompany.coding.sg.banking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.coding.sg.banking.view.OperationView;

/**
 * @author kimchhun2
 *
 */
@RestController
@RequestMapping(path = "/mybank")
public class BankController {

	@Autowired
	private BankServices bankServices;

	@RequestMapping(path="/sayhelo")
	public String sayHello() {
		return "Helo";
	}

	
	@RequestMapping(path = "/account/{accountNumber}/seehistory/{page}/{size}", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public BankResponse seeHistory(@PathVariable String accountNumber, @PathVariable Integer page, @PathVariable Integer size)
	{
		BankResponse response = new BankResponse();
		response.setServiceCalled("seehistory");
		
		try {
			
			bankServices.seeHistory(accountNumber, page, size, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			BankResponseHelper.set_UNEXPECTED_EXCEPTION(response);
		}
		
		return response;
	}

	
	@PostMapping(path = "/account/{accountNumber}/makedeposit", 
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public BankResponse makeDeposit(@PathVariable String accountNumber, @RequestBody OperationView operationView)
	{
		BankResponse response = new BankResponse();
		response.setServiceCalled("makedeposit");
		
		try {
			
			bankServices.makeDeposit(accountNumber, operationView, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			BankResponseHelper.set_UNEXPECTED_EXCEPTION(response);
		}
		
		return response;
	}
	

	@PostMapping(path = "/account/{accountNumber}/makewithdrawal", 
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public BankResponse makeWithdrawal(@PathVariable String accountNumber, @RequestBody OperationView operationView)
	{
		BankResponse response = new BankResponse();
		response.setServiceCalled("makewithdrawal");
		
		try {
			
			bankServices.makeWithdrawal(accountNumber, operationView, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			BankResponseHelper.set_UNEXPECTED_EXCEPTION(response);
		}
		
		return response;
	}

	
	
	
}
