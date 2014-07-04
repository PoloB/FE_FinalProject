import java.util.Vector;


public class FinancialProductB extends FinancialProduct {

	public FinancialProductB(Context context)
	{
		startDate = new Date(1,5,2014);
		endDate = new Date(20,4,2017);
		knockInValue = 0.6f;
		knockOutValue = 1.05f;
		interestRate = 0.015f;
		numberOfPaymentPerYear = 2;
		
		interestPaymentDates = new Vector<Date>();
		interestPaymentDates.addElement(new Date(20,4,2014));
		interestPaymentDates.addElement(new Date(20,10,2014));
		
		startingMarketPrices = new Vector<Float>();
		
		for(int i=0; i< context.getNumberOfMarketPrice(); ++i)
		{
			startingMarketPrices.add(context.getMarketPrice(i).getCurrentPrice());
		}
	}
	
	public void calculateInterestRate(Vector<MarketPrice> mP) {}
	
}
