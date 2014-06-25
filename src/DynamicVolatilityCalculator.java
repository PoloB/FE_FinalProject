import java.util.Vector;


public class DynamicVolatilityCalculator extends VolatilityCalculator {

	DynamicVolatilityCalculator() {}
	
	public void initialize(MarketPrice marketPrice)
	{
		float volatility = 0.f;
		Vector<Float> historicalData = marketPrice.getHistoricalData();
		
		/* Changes u(i) = (S(i)-S(i-1))/S(i-1)) 
		   Mean of u = 0;	            */
		Vector<Float> historicalChanges = new Vector<Float>();
		for(int i=0; i<historicalData.size()-1; i++)
			historicalChanges.addElement( (historicalData.elementAt(i)-historicalData.elementAt(i+1)) / historicalData.elementAt(i+1) );
		
		/* Approximated volatility of u(i) */
		for (int i=0; i<historicalChanges.size(); i++)
			volatility += Math.pow(historicalChanges.elementAt(i),2);
		marketPrice.setVolatility( (float)Math.sqrt(volatility / historicalChanges.size()) );
		
		marketPrice.setHistoricalReturn(historicalChanges);
	}
	
	public void update(MarketPrice marketPrice)
	{
		/* EWMA method (GARCH(1,1) with omega=0) */
		
		float volatility = marketPrice.getVolatility();
		Vector<Float> historicalData = marketPrice.getHistoricalData();
		Vector<Float> historicalChange = marketPrice.getHistoricalReturn(); 
		
		/* Weight of previous volatility */
		float lambda = 0.94f;
		
		/* u(n-1) */
		float mostRecentChange = (historicalData.elementAt(0)-historicalData.elementAt(1)) / historicalData.elementAt(1);
		
		/* New volatility */
		marketPrice.setVolatility( (float)Math.sqrt( lambda*Math.pow(volatility,2)+(1-lambda)*Math.pow(mostRecentChange,2) ) );
	
		historicalChange.removeElementAt( historicalChange.size()-1 );
		historicalChange.insertElementAt( mostRecentChange,0 );
		marketPrice.setHistoricalReturn( historicalChange );
	}
	
}
