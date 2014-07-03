import java.util.Vector;


public class StaticCorrelationCalculator extends CorrelationCalculator {

	public void initialize(MarketPrice marketPrice1, MarketPrice marketPrice2)
	{
		/*float covariance = 0.f;
		Vector<Float> Returns1 = marketPrice1.getHistoricalReturn();
		Vector<Float> Returns2 = marketPrice2.getHistoricalReturn();
		
		for(int i=0; i<Returns1.size(); i++)
			covariance += Returns1.elementAt(i)*Returns2.elementAt(i);
		covariance /= Returns1.size();
		covariance -= marketPrice1.getMean()*marketPrice2.getMean();
		
		float correlation = 0.f;
		correlation = covariance/ ((float)Math.pow(marketPrice1.getVolatility()*marketPrice2.getVolatility(),2));
		marketPrice1.setCorrelation( correlation );*/
	}
	
	public void update(MarketPrice marketPrice1, MarketPrice marketPrice2) {}
	
}
