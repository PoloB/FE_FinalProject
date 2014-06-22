
public abstract class VolatilityCalculator {

	protected float volatility;
	
	public abstract void update();
	
	public float getVolatility() {
		return volatility;
	}
	
}
