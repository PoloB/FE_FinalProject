import java.util.Random;
import java.util.Vector;


public class StdMCSimulator extends MCSimulator
{
	public StdMCSimulator()
	{
		
	}
	
	public void execute(Context context)
	{
		//Get the number of samples
		int nbrSample = context.getNumberOfSample();
		int nbrMarketPrice = context.getNumberOfMarketPrice();
		
		//Generate a uniformly distributed sample vector
		Vector<Float> vec = new Vector<Float> (nbrSample);
		Vector<Vector<Float> > sampleVector = new Vector<Vector<Float> > (nbrMarketPrice);
		
		Random rand = new Random();
		
		for(int j=0; j<nbrMarketPrice; ++j)
		{
			for(int i=0; i<nbrSample; ++i)
				vec.setElementAt(rand.nextFloat(), i);
			
			sampleVector.setElementAt(vec, j);
		}
		
		//TO DO!!! Transform the uniformly distributed sample vector using a geometric brownian motion process
		
		//Create Market Price samples
		Vector<MarketPrice> mvec = new Vector<MarketPrice> (nbrSample);
		Vector<Vector<MarketPrice> > marketPriceSamples = new Vector<Vector<MarketPrice> > (nbrMarketPrice);
		
		for(int j=0; j<nbrMarketPrice; ++j)
		{
			for(int i=0; i<nbrSample; ++i)
				mvec.setElementAt(context.getMarketPrice(j), i);
			
			marketPriceSamples.setElementAt(mvec, j);
		}
		
		//Increment the current day by one
		
		//if today is a closing price day, generate a new path
			//MultiPathGenerator creation
		//else
			//Recopy the value of yesterday
		
		//
		//Get the gain for our product today into the result buffer
		for(int i=0; i < nbrSample; ++i)
		{
			Vector<MarketPrice> marketPriceGroup = new Vector<MarketPrice>();
			marketPriceGroup.clear();
			for(int j=0; j < nbrMarketPrice; ++j)
			{
				marketPriceGroup.add(marketPriceSamples.get(j).get(i));
			}
			
			context.getFinancialProduct().calculateInterestRate(marketPriceGroup);
		}
		
	}
}
