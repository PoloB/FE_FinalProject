import java.util.Vector;


public abstract class VolatilityCalculator
{
	
	public abstract void initialize(MarketPrice marketPrice);
	public abstract void update(MarketPrice marketPrice);
	
}
