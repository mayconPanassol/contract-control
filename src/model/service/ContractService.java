package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;
	

	public ContractService(OnlinePaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {
		
		Date datePayment = contract.getDate();
		Calendar cal = Calendar.getInstance();
		
		Double amountTotal;
		
		for (int i = 1; i <= months; i++) {

			cal.setTime(datePayment);
			cal.add(Calendar.MONTH, 1);
			datePayment = cal.getTime();
			
			amountTotal = paymentService.interest(contract.getTotalValue()/months, i);
			amountTotal = paymentService.paymentFee(amountTotal);
			
			Installment installment = new Installment(datePayment, amountTotal);
			
			contract.setList(installment);

		}
		
		
	}
	                                                              
	
}
