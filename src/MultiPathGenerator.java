import java.util.Vector;

//import umontreal.iro.lecuyer.rng.MRG32k3a;
//import umontreal.iro.lecuyer.stochprocess.GeometricBrownianMotion;


public class MultiPathGenerator
{
	private boolean volatilityIsDynamic;
	
	
	public MultiPathGenerator(boolean isDynamic)
	{		
		volatilityIsDynamic = isDynamic;
	}
	
	private void generateNewClosingPrice(MarketPricePath marketPricePath, float correlation)
	{
		
	}
	
	public void nextPathFor(Vector<MarketPricePath> marketSamples, Vector<Vector<Float> > randomVector)
	{
		Date today = Context.get().getCurrentDay();
		
		for(int i=0; i < marketSamples.size(); ++i)
		{
			MarketPricePath currentEvaluatedMarketPath = marketSamples.get(i); 
			Vector<MarketPrice> currentMarketPrices = currentEvaluatedMarketPath.getMarketPrices();
			
			//Calculate the correlation in the sample
			float correlation = currentEvaluatedMarketPath.getCovariance() / (currentMarketPrices.get(0).getVolatility() * currentMarketPrices.get(1).getVolatility() * currentMarketPrices.get(0).getHistoricalData().size());
			Vector<Float> closingPrices = new Vector<Float>(currentMarketPrices.size());
			
			if(volatilityIsDynamic)
			{
				for(int j=0; j < currentMarketPrices.size(); ++i)
				{
					//Delete the last term in the covariance
					MarketPrice currentMarketPrice = currentMarketPrices.get(j);
					Vector<Float> currentHD = currentMarketPrices.get(j).getHistoricalData();
					currentHD.removeElementAt(currentHD.size()-1);
					
					//Generate new closing price
					//TODO
					closingPrices.setElementAt(1.f, j);
					
					if(currentMarketPrices.get(j).hasClosingPrice(today))
					{
						//Add the new closing price in the historical data
						currentHD.insertElementAt(closingPrices.get(j), 0);
						currentMarketPrice.setCurrentPrice(closingPrices.get(j));
						
					}
					
					else
					{
						//Push zero if there is no closing price today
						currentHD.insertElementAt(-1.f, 0);
					}
				}
				
				//Update the covariance
				float newCovariance = currentEvaluatedMarketPath.getCovariance() + closingPrices.get(0) * closingPrices.get(1);
				currentEvaluatedMarketPath.setCovariance(newCovariance);
				
				//Update the volatility
				for(int j=0; j < currentMarketPrices.size(); ++j)
				{
					if(currentMarketPrices.get(j).getHistoricalData().get(0) > 0.f)
					{
						/* EWMA method (GARCH(1,1) with omega=0) */
						
						float volatility = currentMarketPrices.get(j).getVolatility();
						Vector<Float> historicalData = currentMarketPrices.get(j).getHistoricalData();
						
						/* Weight of previous volatility */
						float lambda = 0.94f;
						
						/* u(n-1) */
						float mostRecentChange = (historicalData.elementAt(0)-historicalData.elementAt(1)) / historicalData.elementAt(1);
						
						/* New volatility */
						currentMarketPrices.get(j).setVolatility( (float)Math.sqrt( lambda*Math.pow(volatility,2)+(1-lambda)*Math.pow(mostRecentChange,2) ) );
					}
				}
			}
			
			else
			{
				for(int j=0; j < currentMarketPrices.size(); ++i)
				{
					//Delete the last term in the covariance
					MarketPrice currentMP = currentMarketPrices.get(j);
					
					//Generate new closing price
					//TODO
					closingPrices.setElementAt(1.f, j);
					
					if(currentMarketPrices.get(j).hasClosingPrice(today))
					{
						//Add the new closing price in the historical data
						currentMP.setCurrentPrice(closingPrices.get(j));
					}
				}
			}
		}
	}
}
