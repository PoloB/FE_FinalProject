import java.util.Vector;


public abstract class VolatilityCalculator {

	protected MarketPrice marketPrice;
	protected float volatility;
	
	public abstract void initialize();
	public abstract void update();
	
}
