import java.util.Vector;


public abstract class VolatilityCalculator {

	protected MarketPrice marketPrice;
	
	public abstract void initialize();
	public abstract void update();
	
}
