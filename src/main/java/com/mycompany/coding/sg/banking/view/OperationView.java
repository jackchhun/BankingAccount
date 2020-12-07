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
public class OperationView {

	private LocalDateTime datetime = LocalDateTime.now();
	
	private Double amount = 0.0;
	
	private String title = "";
	
	private String operationType = "";

}
