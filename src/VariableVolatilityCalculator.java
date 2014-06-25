import java.util.Vector;


public class VariableVolatilityCalculator extends VolatilityCalculator {

	VariableVolatilityCalculator() {}
	
	public void initialize(MarketPrice marketPrice)
	{
		float volatility = 0.f;
		Vector<Float> historicalData = marketPrice.getHistoricalData();
		
		/* u(i) = (S(i)-S(i-1))/S(i-1)) */
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		for(int i=0; i<historicalData.size()-1; i++)
		{
			historicalDataManipulated.addElement(
					(historicalData.elementAt(i)-historicalData.elementAt(i+1))/historicalData.elementAt(i+1)
												);
		}
		
		/* Approximated volatility of u(i) */
		for (int i=0; i<historicalDataManipulated.size(); i++)
		{
			volatility += Math.pow(historicalDataManipulated.elementAt(i),2);
		}
		marketPrice.setVolatility( (float)Math.sqrt(volatility / historicalDataManipulated.size()) );
	}
	
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
