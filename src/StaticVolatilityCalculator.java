import java.util.Vector;


public class StaticVolatilityCalculator extends VolatilityCalculator
{

	StaticVolatilityCalculator() {}
	
	public void initialize(MarketPrice marketPrice)
	{
		float volatility = 0.f;
		Vector<Float> historicalData = marketPrice.getHistoricalData();

		/* Returns u(i) = ln(S(i)/S(i-1)) */
		Vector<Float> historicalReturn= new Vector<Float>();
		for(int i=0; i<historicalData.size()-1; i++)
			historicalReturn.addElement ( (float)Math.log( historicalData.elementAt(i+1) / (historicalData.elementAt(i) )));

		/* Mean of u(i) */
		float mean = 0.f;
		for(int i=0; i<historicalReturn.size(); i++)
			mean += historicalReturn.elementAt(i);
		mean /= historicalReturn.size();
		marketPrice.setMean( mean );
		
		/* Volatility of u(i) */
		for (int i=0; i<historicalReturn.size(); i++)
			volatility += Math.pow(historicalReturn.elementAt(i)-mean,2);
		volatility = (float)Math.sqrt(volatility / historicalReturn.size()-1);
		marketPrice.setVolatility( volatility );
		
		marketPrice.setHistoricalReturn( historicalReturn );
	}
	
	public void update(MarketPrice marketPrice) {}
	
}
