/**
 * 
 */
package com.mycompany.coding.sg.banking.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.coding.sg.banking.domain.AccountCD;
import com.mycompany.coding.sg.banking.domain.AccountRepository;
import com.mycompany.coding.sg.banking.domain.BalanceCD;
import com.mycompany.coding.sg.banking.domain.CustomerRepository;
import com.mycompany.coding.sg.banking.domain.OperationCD;
import com.mycompany.coding.sg.banking.domain.OperationRepository;
import com.mycompany.coding.sg.banking.view.BalanceView;
import com.mycompany.coding.sg.banking.view.OperationView;
import com.mycompany.coding.sg.banking.view.ServiceHelper;


/**
 * @author kimchhun2
 *
 */
@Service
public class BankServices {

	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private AccountRepository accountDao;
	
	@Autowired
	private OperationRepository operationDao;

	public final static int PAGESIZE_MAX = 50;
	public final static int DEFAULT_PAGE = 0;
	public final static int DEFAULT_SIZE = PAGESIZE_MAX;

	@Transactional
	public void seeHistory(String accountNumber, Integer page, Integer size, BankResponse response) {
		
		try {
			
			AccountCD account = accountDao.findByAccountNumber(accountNumber);
			
			if (account != null) {
				
				// Get the balance
				BalanceCD balance = account.getBalance();
				
				if (balance == null) {
					balance = new BalanceCD();
					account.setBalance(balance);
				}				

				// Get the history
				int requestpage = page;
				int requestsize = size;
				if (size > PAGESIZE_MAX) {
					requestsize = PAGESIZE_MAX;
				}
				Pageable pagingElements = PageRequest.of(requestpage, requestsize);
				
				Page<OperationCD> resultCD = operationDao.findByAccountNumberWithPaging(accountNumber, pagingElements);
				
				// Convert to View for history
				Page<OperationView> resultView = ServiceHelper.convertToView(resultCD);
				response.setHistory(resultView);
				
				// Convert for balance
				BalanceView balanceView = ServiceHelper.convertToView(accountNumber, balance);
				response.setBalance(balanceView);
				
				BankResponseHelper.setOK(response);
				
			} else {
				BankResponseHelper.set_ACCOUNT_NOT_FOUND(accountNumber, response);
			}		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	@Transactional
	public void makeDeposit(String accountNumber, OperationView operationView, BankResponse response) {
	
		try {
			
			AccountCD account = accountDao.findByAccountNumber(accountNumber);
			
			if (account != null) {
				
				double amount = Math.abs(operationView.getAmount());
				
				// Get the balance
				BalanceCD balance = account.getBalance();
				
				if (balance == null) {
					balance = new BalanceCD();
					account.setBalance(balance);
				}				

				// Amount should not be 0.00
				if (amount == 0.0d) {
					BankResponseHelper.set_CANNOT_MAKE_DEPOSIT_WITH_ZERO_AMOUNT(response);
				} 
				
				// Proceed
				else {
					OperationCD operationToAdd = ServiceHelper.convertToModel(operationView);
					
					makeOperation(operationToAdd, account, "Deposit", amount);
					
					accountDao.save(account);

					BankResponseHelper.setOK(response);
				}
			} else {
				BankResponseHelper.set_ACCOUNT_NOT_FOUND(accountNumber, response);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			BankResponseHelper.set_UNEXPECTED_EXCEPTION(response);
		}
	}


	private void makeOperation(OperationCD operationToAdd, AccountCD account, String operation, double amount) {
		
		BalanceCD balance = account.getBalance();
		
		LocalDateTime now = LocalDateTime.now();
		balance.setDatetime(now);
		
		operationToAdd.setDatetime(now);
		operationToAdd.setOperationType(operation);
		operationToAdd.setAccount(account);
		operationToAdd.setAmount(amount);
		
		account.getAllOperations().add(operationToAdd);
		
		balance.setBalance(balance.getBalance() + amount);
	}
	

	@Transactional
	public void makeWithdrawal(String accountNumber, OperationView operationView, BankResponse response) {
		
		try {
			
			AccountCD account = accountDao.findByAccountNumber(accountNumber);
			
			if (account != null) {
				
				double amount = Math.abs(operationView.getAmount());
				
				// Get the balance
				BalanceCD balance = account.getBalance();
				
				if (balance == null) {
					balance = new BalanceCD();
					account.setBalance(balance);
				}				
				
				// Amount should not be 0.00
				if (amount == 0.0d) {
					BankResponseHelper.set_CANNOT_MAKE_WITHDRAWAL_WITH_ZERO_AMOUNT(response);
				} 
				
				// Balance should not be negative
				else if (balance.getBalance() - amount < 0.0d) {
					BankResponseHelper.set_NOT_ENOUGH_CASH_IN_ACCOUNT(response, amount, balance.getBalance());
				} 
				
				// Proceed
				else {
					OperationCD operationToAdd = ServiceHelper.convertToModel(operationView);
					
					makeOperation(operationToAdd, account, "Withdraw", -amount);
					
					accountDao.save(account);

					BankResponseHelper.setOK(response);
				} 
			} else {
				BankResponseHelper.set_ACCOUNT_NOT_FOUND(accountNumber, response);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			BankResponseHelper.set_UNEXPECTED_EXCEPTION(response);
		}
	}


}
