import java.util.Vector;


public class FinancialProductB extends FinancialProduct {

	public FinancialProductB(Context context)
	{
		startDate = context.getCurrentDay();
		int endYear = startDate.getYear() + 3;
		endDate = new Date(startDate.getDay(),startDate.getMonth(),endYear);
		knockInValue = 0.6f;
		knockOutValue = 1.05f;
		interestRate = 0.03f;
		numberOfPaymentPerYear = 2;
		
		interestPaymentDates = new Vector<Date>();
		
		//Calculate the interest payment dates
		
		interestPaymentDates.addElement(new Date(20,10,2014));
		interestPaymentDates.addElement(new Date(20,4,2015));
		interestPaymentDates.addElement(new Date(20,10,2015));
		interestPaymentDates.addElement(new Date(20,4,2016));
		interestPaymentDates.addElement(new Date(20,10,2016));
		interestPaymentDates.addElement(new Date(20,4,2017));
		
		endDate = new Date(20,4,2017);
		
		startingMarketPrices = new Vector<Float>();
		
		for(int i=0; i< context.getNumberOfMarketPrice(); ++i)
		{
			startingMarketPrices.add(context.getMarketPrice(i).getCurrentPrice());
		}
	}
	
}
