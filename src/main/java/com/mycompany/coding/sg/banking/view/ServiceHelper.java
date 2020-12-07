/**
 * 
 */
package com.mycompany.coding.sg.banking.view;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mycompany.coding.sg.banking.domain.BalanceCD;
import com.mycompany.coding.sg.banking.domain.OperationCD;

/**
 * @author kimchhun2
 *
 */
public class ServiceHelper {

	public static Page<OperationView> convertToView(Page<OperationCD> operationPage) {
		Pageable pageable = operationPage.getPageable();
		List<OperationCD> content = operationPage.getContent();
		long total = operationPage.getTotalElements();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<OperationView> allOperationViews = new ArrayList<>();
		content.stream().forEach(stock -> {
			OperationView stockView = modelMapper.map(stock, OperationView.class);
			allOperationViews.add(stockView);
		});
		
		PageImpl<OperationView> page = new PageImpl<>(allOperationViews, pageable, total);
		return page;

	}

	public static OperationCD convertToModel(OperationView operationView) {
		ModelMapper modelMapper = new ModelMapper();
		
		OperationCD operationCD = modelMapper.map(operationView, OperationCD.class);
		
		return operationCD;
	}

	public static BalanceView convertToView(String accountNumber, BalanceCD balanceCD) {
		BalanceView balanceView = new BalanceView();
		balanceView.setAccountNumber(accountNumber);
		balanceView.setBalance(balanceCD.getBalance());
		balanceView.setDatetime(balanceCD.getDatetime());
		return balanceView;
	}

	

}
