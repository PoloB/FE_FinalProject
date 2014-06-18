import java.util.Vector;


public abstract class MarketPrice
{	
	private MarketPrice coralatedSharePrice;
	protected float volatility;
	protected float currentPrice;
	private String name;
	private Vector<Integer> weeklyClosedDays;
	private Vector<Date> holydays;
	private Vector<Float> historicalData;
	
	MarketPrice()
	{
		
	}
	
	public abstract void getNextPath();
}


