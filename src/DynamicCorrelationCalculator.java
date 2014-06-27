import java.util.Vector;


public class DynamicCorrelationCalculator extends CorrelationCalculator {

	public void initialize(MarketPrice marketPrice1, MarketPrice marketPrice2)
	{
		float covariance = 0.f;
		Vector<Float> changes1 = marketPrice1.getHistoricalReturn();
		Vector<Float> changes2 = marketPrice2.getHistoricalReturn();
		
		for(int i=0; i<changes1.size(); i++)
			covariance += changes1.elementAt(i)*changes2.elementAt(i);
		
		covariance /= changes1.size();
		
		float correlation = 0.f;
		correlation = covariance / ((float)Math.pow(marketPrice1.getVolatility()*marketPrice2.getVolatility(),2));
		marketPrice1.setCorrelation( correlation );
	}
	
	public void update(MarketPrice marketPrice1, MarketPrice marketPrice2)
	{
		/* EWMA model */
		float covariance = 0.f;
		Vector<Float> changes1 = marketPrice1.getHistoricalReturn();
		Vector<Float> changes2 = marketPrice2.getHistoricalReturn();
		
//		/* Weight of previous covariance */
//		float lambda = 0.94f;
		
		/* New covariance */
		for(int i=0; i<changes1.size(); i++)
			covariance += changes1.elementAt(i)*changes2.elementAt(i);
		covariance /= changes1.size();
	
		/* New correlation */
		marketPrice1.setCorrelation( covariance / (float)Math.pow(marketPrice1.getVolatility()*marketPrice2.getVolatility(),2) );
		
	}
	
}
