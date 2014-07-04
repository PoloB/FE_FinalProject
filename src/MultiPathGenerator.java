import java.util.Vector;

import umontreal.iro.lecuyer.probdist.NormalDist;

//import umontreal.iro.lecuyer.rng.MRG32k3a;
//import umontreal.iro.lecuyer.stochprocess.GeometricBrownianMotion;


/*
This class is used to generate day per day evolution of a sample vector of market price
*/

public class MultiPathGenerator
{
	private boolean volatilityIsDynamic;
	
	
	public MultiPathGenerator(boolean isDynamic)
	{		
		volatilityIsDynamic = isDynamic;
	}
	
	public void nextPathFor(Vector<MarketPricePath> marketSamples, Vector<Vector<Float> > randomVector)
	{
		Date today = Context.get().getCurrentDay();
		int numberOfSubStep = randomVector.size() / marketSamples.size();
		int numberOfSample = marketSamples.size();
		
		for(int i=0; i < numberOfSample; ++i)
		{
			//System.out.println(randomVector.get(i).get(0));
			//Get the current market path and market price vector
			MarketPricePath currentEvaluatedMarketPath = marketSamples.get(i); 
			Vector<MarketPrice> currentMarketPrices = currentEvaluatedMarketPath.getMarketPrices();
			
			//Set the time step
			Vector<Float> timeStep = new Vector<Float>();
			for(int j=0; j< currentMarketPrices.size(); ++j)
				timeStep.add(1.f/currentMarketPrices.get(j).getNumberOfClosingDayPerYear());
			
			//Calculate the correlation in the sample
			float correlation = currentEvaluatedMarketPath.getCovariance() / (currentMarketPrices.get(0).getVolatility() * currentMarketPrices.get(1).getVolatility());
			Vector<Float> closingPrices = new Vector<Float>();
			for(int k=0; k<currentMarketPrices.size(); ++k)
				closingPrices.add(currentMarketPrices.get(k).getCurrentPrice());
			
			if(volatilityIsDynamic)
			{
				closingPrices.clear();
				for(int j=0; j < currentMarketPrices.size(); ++j)
				{
					//Delete the last term in the covariance
					MarketPrice currentMarketPrice = currentMarketPrices.get(j);
					Vector<Float> currentHD = currentMarketPrices.get(j).getHistoricalData();
					currentHD.removeElementAt(currentHD.size()-1);
					
					//Generate new closing price
					float gbm = 0.f;
					float mu = 0.01f;
					float cVolatility = currentMarketPrice.getVolatility();
					
					closingPrices.add((float) NormalDist.inverseF01(randomVector.get(i).get(j))); 
					
					if(j==1)
					{
						//Modify the second normaly distributed sample based on the correlation
						float firstNormalSample = closingPrices.get(0);
						float secondNormalSample = closingPrices.get(1);
						closingPrices.setElementAt((float) (firstNormalSample * correlation + Math.sqrt(1-correlation * correlation) * secondNormalSample), j); 
					}
					
					gbm = (float) (currentMarketPrice.getCurrentPrice() * Math.exp( (mu - 0.5f * cVolatility) * timeStep.get(j) + cVolatility*Math.sqrt(timeStep.get(j)) * closingPrices.get(j)));
					
					closingPrices.setElementAt(gbm, j);
					
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
				currentEvaluatedMarketPath.updateCovrariance(closingPrices.get(0), closingPrices.get(1));
				
				//Update the volatility
				for(int j=0; j < currentMarketPrices.size(); ++j)
				{
					MarketPrice currentMarketPrice = currentMarketPrices.get(j);
					
					if(currentMarketPrice.getHistoricalData().get(0) > 0.f)
					{
						/* EWMA method (GARCH(1,1) with omega=0) */
						
						float volatility = currentMarketPrice.getVolatility();
						Vector<Float> historicalData = currentMarketPrice.getHistoricalData();
						
						/* Weight of previous volatility */
						float lambda = 0.94f;
						
						/* u(n-1) */
						float mostRecentChange = (historicalData.elementAt(0)-historicalData.elementAt(1)) / historicalData.elementAt(1);
						
						/* New volatility */
						currentMarketPrice.setVolatility( (float)Math.sqrt( lambda*Math.pow(volatility,2)+(1-lambda)*Math.pow(mostRecentChange,2) ) );
					}
				}
			}
			
			else
			{
				Vector<Float> normalSamples = new Vector<Float> ();
				
				for(int k=0; k<numberOfSubStep; ++k)
				{
					normalSamples.clear(); 
					
					for(int j=0; j < currentMarketPrices.size(); ++j)
					{	
						float rand = randomVector.get(i*numberOfSubStep + k).get(j);
						normalSamples.add((float) NormalDist.inverseF01(rand));
						
						MarketPrice currentMarketPrice = currentMarketPrices.get(j);
						
						//Generate new closing price
						float gbm = 0.f;
						float mu = 0.01f;
						float cVolatility = currentMarketPrice.getVolatility();
						float currentPrice = currentMarketPrice.getCurrentPrice();
						float currentTimeStep = timeStep.get(j) / (float)numberOfSubStep;
						
						if(j==1)
						{
							float firstNormalSample = normalSamples.get(0);
							float secondNormalSample = normalSamples.get(1);
							normalSamples.setElementAt((float) (firstNormalSample * correlation + Math.sqrt(1-correlation * correlation) * secondNormalSample), 1);
						}
	
						gbm = (float) (currentPrice * Math.exp( (mu - 0.5f * cVolatility*cVolatility) * currentTimeStep + cVolatility*Math.sqrt(currentTimeStep) * normalSamples.get(j)));
						currentPrice = gbm;
						
						closingPrices.setElementAt(gbm, j);
					}
				}
				
				for(int j=0; j < currentMarketPrices.size(); ++j)
				{
					MarketPrice currentMarketPrice = currentMarketPrices.get(j);
					Vector<Float> currentHD = currentMarketPrices.get(j).getHistoricalData();
					
					if(currentMarketPrice.hasClosingPrice(today))
					{
						//Add the new closing price in the historical data
						currentHD.insertElementAt(currentMarketPrice.getCurrentPrice(), 0);
						currentMarketPrice.setCurrentPrice(closingPrices.get(j));
						currentHD.remove(currentHD.size()-1);
					}
					
					else
					{
						//Push zero if there is no closing price today
						currentHD.insertElementAt(-1.f, 0);
						currentHD.remove(currentHD.size()-1);
					}
				}
			}
		}
	}
}
