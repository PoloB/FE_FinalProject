
public abstract class MarketPrice {
	
	private MarketPrice correlatedSharePrice;
	protected Volatility volatility;
	protected float currentPrice;
	
	public abstract void getNextPath();
}


