import java.util.Vector;


public abstract class VolatilityCalculator
{

//	protected float volatility;
//	
//	public float getVolatility() { return volatility; }
	
	public abstract void initialize(MarketPrice marketPrice);
	public abstract void update(MarketPrice marketPrice);
	
}
