
public abstract class MarketPrice {
	
	private MarketPrice coraletedSharePrice;
	protected Volatility volatility;
	protected float currentPrice;
	
	public abstract void getNextPath();
}


