package com.mycompany.coding.sg.banking.web;

public class BankResponseHelper {

	public static void setOK(BankResponse response) {
		response.setReturnCode("200");
		response.setReturnMessage("OK");
	}

	public static void set_UNEXPECTED_EXCEPTION(BankResponse response) {
		response.setReturnCode("900");
		response.setReturnMessage("Unexpected error occurred while processing. Operation aborted. Call our bank hotline at +33 102030405 or send us an email at hotline@mycompany.com.");
	}

	public static void set_ACCOUNT_NOT_FOUND(String accountNumber, BankResponse response) {
		response.setReturnCode("400");
		response.setReturnMessage("Account with the number " + accountNumber + " does not exist. Operation aborted. Call our bank hotline or send an email.");
	}
	
	public static void set_CANNOT_MAKE_DEPOSIT_WITH_ZERO_AMOUNT(BankResponse response) {
		response.setReturnCode("402");
		response.setReturnMessage("Cannot make a deposit with 0.00 amount. Operation aborted. Check the amount and try again.");
	}

	public static void set_CANNOT_MAKE_WITHDRAWAL_WITH_ZERO_AMOUNT(BankResponse response) {
		response.setReturnCode("403");
		response.setReturnMessage("Cannot make a withdrawal with 0.00 amount. Operation aborted. Check the amount and try again.");
	}

	public static void set_NOT_ENOUGH_CASH_IN_ACCOUNT(BankResponse response, Double amount, Double balance) {
		response.setReturnCode("404");
		response.setReturnMessage("Not enough cash. Balance is " + balance.toString() + ". Cannot make a withdrawal with " + amount.toString() + ". Operation aborted. Check the amount and try again.");
	}



	
	
}
