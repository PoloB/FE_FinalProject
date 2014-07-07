import java.util.Vector;


public class FinancialProductA extends FinancialProduct
{
	public FinancialProductA(Context context)
	{
		startDate = context.getCurrentDay();
		int endYear = startDate.getYear() + 5;
		endDate = new Date(startDate.getDay(),startDate.getMonth(),endYear);
		knockInValue = 0.49f;
		knockOutValue = 1.05f;
		interestRate = 0.057f;
		numberOfPaymentPerYear = 4;
		
		interestPaymentDates = new Vector<Date>();
		interestPaymentDates.addElement(new Date(23,7,2014));
		interestPaymentDates.addElement(new Date(23,10,2014));
		interestPaymentDates.addElement(new Date(23,1,2015));
		interestPaymentDates.addElement(new Date(23,4,2015));
		interestPaymentDates.addElement(new Date(23,7,2015));
		interestPaymentDates.addElement(new Date(23,10,2015));
		interestPaymentDates.addElement(new Date(23,1,2016));
		interestPaymentDates.addElement(new Date(23,4,2016));
		interestPaymentDates.addElement(new Date(23,7,2016));
		interestPaymentDates.addElement(new Date(23,10,2016));
		interestPaymentDates.addElement(new Date(23,1,2017));
		interestPaymentDates.addElement(new Date(23,4,2017));
		interestPaymentDates.addElement(new Date(23,7,2017));
		interestPaymentDates.addElement(new Date(23,10,2017));
		interestPaymentDates.addElement(new Date(23,1,2018));
		interestPaymentDates.addElement(new Date(23,4,2018));
		interestPaymentDates.addElement(new Date(23,7,2018));
		interestPaymentDates.addElement(new Date(23,10,2018));
		interestPaymentDates.addElement(new Date(23,1,2019));
		interestPaymentDates.addElement(new Date(23,4,2019));
		
		endDate = new Date(23,4,2019);
		
		startingMarketPrices = new Vector<Float>();
		
		for(int i=0; i< context.getNumberOfMarketPrice(); ++i)
		{
			startingMarketPrices.add(context.getMarketPrice(i).getCurrentPrice());
		}
	}
	
}
