import java.util.Vector;


public abstract class FinancialProduct
{
//	private String name;
	protected Date startDate;
	protected Date endDate;
	protected float knockInValue;
	protected float knockOutValue;
	protected float interestRate;
//	protected float interestPaymentInterval;
	protected Vector<Date> interestPaymentDates;
	
//	boolean 
	
	
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
	
//	public void calculateInterestPaymentDates() {
//		// TO DO
//	}
	
	public abstract void calculateInterestRate(Vector<MarketPrice> mP);
	
}
