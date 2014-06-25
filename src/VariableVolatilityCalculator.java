import java.util.Vector;


public class VariableVolatilityCalculator extends VolatilityCalculator
{

	VariableVolatilityCalculator() {}
	
	public void update(MarketPrice marketPrice)
	{
		/* EWMA method (GARCH(1,1) with omega=0) */
		
		float volatility = marketPrice.getVolatility();
		Vector<Float> historicalData = marketPrice.getHistoricalData();
		
		/* Weight of previous volatility */
		float lambda = 0.94f;
		
		/* u(n-1) */
		float lastPercentageChange = 
				(historicalData.elementAt(0)-historicalData.elementAt(1))/historicalData.elementAt(1);
		
		/* New volatility */
		marketPrice.setVolatility((float) 
				Math.sqrt(lambda*Math.pow(volatility,2)+(1-lambda)*Math.pow(lastPercentageChange,2)));
	}
}
