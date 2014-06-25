import java.util.Vector;


public class ConstantVolatilityCalculator extends VolatilityCalculator
{

	ConstantVolatilityCalculator() {}
	
	public void initialize(MarketPrice marketPrice)
	{
		float volatility = 0.f;
		Vector<Float> historicalData = marketPrice.getHistoricalData();

		/* u(i) = ln(S(i)/S(i-1)) */
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		for(int i=0; i<historicalData.size()-1; i++)
			historicalDataManipulated.addElement ( (float)Math.log( historicalData.elementAt(i+1) / (historicalData.elementAt(i) )));

		/* Mean of u(i) */
		float mean = 0.f;
		for(int i=0; i<historicalDataManipulated.size(); i++)
			mean += historicalDataManipulated.elementAt(i);
		mean /= historicalDataManipulated.size();
		
		/* Volatility of u(i) */
		for (int i=0; i<historicalDataManipulated.size(); i++)
			volatility += Math.pow(historicalDataManipulated.elementAt(i)-mean,2);
		
		marketPrice.setVolatility( (float)Math.sqrt(volatility / historicalDataManipulated.size()-1) );
	}
	
	public void update(MarketPrice marketPrice) {}
	
}
