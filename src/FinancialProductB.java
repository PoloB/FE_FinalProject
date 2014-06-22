import java.util.Vector;


public class FinancialProductB extends FinancialProduct {

	public FinancialProductB() {
		startDate = new Date(1,5,2014);
		endDate = new Date(20,4,2017);
		knockInValue = 0.6f;
		knockOutValue = 1.05f;
		interestRate = 0.03f;

		interestPaymentDates = new Vector<Date>();
		interestPaymentDates.addElement(new Date(20,4,2014));
		interestPaymentDates.addElement(new Date(20,10,2014));
	}
	
	public void calculateInterestRate(Vector<MarketPrice> mP) {}
	
}
