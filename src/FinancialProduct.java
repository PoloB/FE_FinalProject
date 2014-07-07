import java.util.Vector;


public abstract class FinancialProduct
{
//	private String name;
	protected Date startDate;
	protected Date endDate;
	protected float knockInValue;
	protected float knockOutValue;
	protected float interestRate;
	protected int numberOfPaymentPerYear;
	protected Vector<Date> interestPaymentDates;
	protected Vector<Float> startingMarketPrices;
	
	
	public float getKnockInValue() {
		return knockInValue;
	}
	
	public float getKnockOutValue() {
		return knockOutValue;
	}
	
	public float getInterestRate() {
		return interestRate;
	}
	
	public Vector<Date> getInterestPaymentDates() {
		return interestPaymentDates;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public float getStartingMarketPrice(int i)
	{
		return startingMarketPrices.get(i);
	}
	
	public int getNumberOfPaymentPerYear()
	{
		return numberOfPaymentPerYear;
	}
	
}
