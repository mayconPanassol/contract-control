package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date dateContract = sdf.parse(sc.nextLine());
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		System.out.print("Enter number of installments: ");
		int numberInstallment = sc.nextInt();
		
		Contract contract = new Contract(contractNumber, dateContract, contractValue);
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, numberInstallment);
		
		System.out.println("Installments:");
	
		for (Installment list : contract.getList()) {
			
			System.out.println(sdf.format(list.getDueDate()) + 
			" - " + String.format("%.2f", list.getAmount()));
			
		}

		
		} catch (java.text.ParseException e) {
			System.out.println("Error" + e.getMessage());
		}		
		
		
		sc.close();
	}

}
