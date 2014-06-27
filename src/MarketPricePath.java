import java.util.Vector;

//This class gives the abstract of Sample to a group of market price.
//We'll make this sample evolutes using Monte Carlo

public class MarketPricePath
{
	private Vector<MarketPrice> marketPriceGroup;
	float covariance;
	
	public MarketPricePath(Vector<MarketPrice> mpg)
	{
		marketPriceGroup = new Vector<MarketPrice>(mpg);
		
		//Suppose there are only two market price in the vector
		
		//Calculate the covariance based on the historical data
		int historicalDataSize = mpg.get(0).getHistoricalData().size();
		
		for(int i=1; i < historicalDataSize; ++i)
		{
			//Push ui * vi in the covariance
			covariance += mpg.get(0).getHistoricalData().get(i) * mpg.get(1).getHistoricalData().get(i); 
		}
		
		covariance /= (historicalDataSize-1);
	}
	
	public boolean hasClosingPrice(Date today)
	{
		boolean result = false;
		for(int i=0; i<marketPriceGroup.size(); ++i)
		{
			if(marketPriceGroup.get(i).hasClosingPrice(today))
				result = true;
		}
		
		return result;
	}
	
	public float getCovariance()
	{
		return covariance;
	}
	
	public Vector<MarketPrice> getMarketPrices()
	{
		return marketPriceGroup;
	}
	
	public void setCovariance(float cov)
	{
		covariance = cov;
	}
	
}
