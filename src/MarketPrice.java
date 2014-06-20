import java.util.Vector;


public abstract class MarketPrice
{	
	private MarketPrice correlatedSharePrice;
	protected float volatility;
	protected float currentPrice;
	private String name;
	private Vector<Integer> weeklyClosedDays; // int or Date?
	private Vector<Date> holydays;
	private Vector<Float> historicalData;
	
	public abstract void getNextPath();
	
}


