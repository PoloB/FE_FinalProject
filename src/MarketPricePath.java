import java.util.Vector;

//This class gives the abstract of Sample to a group of market price.
//We'll make this sample evolutes using Monte Carlo

public class MarketPricePath
{
	private Vector<MarketPrice> marketPriceGroup;
	float covariance;
	
	public MarketPricePath(Vector<MarketPrice> mpg)
	{
		marketPriceGroup = mpg;
		
		//Suppose there are only two market price in the vector
		if(Context.get().volatilityIsDynamic())
		{	
			//Calculate the covariance based on the historical data
			int historicalDataSize = mpg.get(0).getHistoricalData().size();
		
			for(int i=0; i < historicalDataSize; ++i)
			{
				float price1 = mpg.get(0).getHistoricalData().get(i);
				float price2 = mpg.get(1).getHistoricalData().get(i);
				int i1 = 1, i2 =1;
				
				if(!(price1 <0.f && price2<0.f))
				{
					while(price1 < 0.f && i+i1 < historicalDataSize-1)
					{
						price1 = mpg.get(0).getHistoricalData().get(i+i1);
						i1++;
					}
					
					while(price2 < 0.f && i+i2 < historicalDataSize-1)
					{
						price2 = mpg.get(0).getHistoricalData().get(i+i2);
						i2++;
					}
					//Push ui * vi in the covariance
					if(price1 > 0.f && price2 > 0.f)
						covariance += price1 * price2;
				}
			}
			
			covariance *= 0.5 * (mpg.get(0).getNumberOfClosingDayPerYear() + mpg.get(1).getNumberOfClosingDayPerYear()) * historicalDataSize / 365.f;
		}
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
	
	void updateCovrariance(float newClosingPriceMarket1, float newClosingPriceMarket2)
	{
		int i1 = 0, i2 =0;
		int historicalDataSize = marketPriceGroup.get(0).getHistoricalData().size();
		
		if(!(newClosingPriceMarket1 <0.f && newClosingPriceMarket2<0.f))
		{
			while(newClosingPriceMarket1< 0.f && i1 < historicalDataSize-1)
			{
				newClosingPriceMarket1 = marketPriceGroup.get(0).getHistoricalData().get(i1);
				i1++;
			}
			
			while(newClosingPriceMarket2 < 0.f && i2 < historicalDataSize-1)
			{
				newClosingPriceMarket2 = marketPriceGroup.get(0).getHistoricalData().get(i2);
				i2++;
			}
			//Push ui * vi in the covariance
			if(newClosingPriceMarket1 > 0.f && newClosingPriceMarket2 > 0.f)
				covariance += newClosingPriceMarket1 * newClosingPriceMarket2 * historicalDataSize / 365.f;
		}
	}
	
}
