package model.service;

public class PaypalService implements OnlinePaymentService{

	@Override
	public double paymentFee(double amount) {
		return amount * 1.02;
	}

	@Override
	public double interest(double amount, int months) {
		return amount + 2 * months;
	}



}
