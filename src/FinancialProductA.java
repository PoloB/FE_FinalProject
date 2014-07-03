import java.util.Vector;


public class FinancialProductA extends FinancialProduct
{
	public FinancialProductA()
	{
		startDate = new Date(23,4,2014);
		endDate = new Date(23,4,2019);
		knockInValue = 0.49f;
		knockOutValue = 1.05f;

		interestPaymentDates = new Vector<Date>();
		interestPaymentDates.addElement(new Date(23,1,2014));
		interestPaymentDates.addElement(new Date(23,4,2014));
		interestPaymentDates.addElement(new Date(23,7,2014));
		interestPaymentDates.addElement(new Date(23,10,2014));
	}
	
	public void calculateInterestRate(Vector<MarketPrice> mP)
	{
	}
	
}
