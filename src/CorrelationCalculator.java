

public abstract class CorrelationCalculator
{
	public abstract void initialize(MarketPrice marketPrice1, MarketPrice marketPrice2);
	public abstract void update(MarketPrice marketPrice1, MarketPrice marketPrice2);
	
}
