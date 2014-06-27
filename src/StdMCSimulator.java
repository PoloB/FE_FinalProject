import java.util.Random;
import java.util.Vector;


public class StdMCSimulator extends MCSimulator
{
	public StdMCSimulator()
	{
		
	}
	
	public void execute()
	{
		Context context = Context.get();
		
		///////////////////////////
		//Initialize the simulation
		///////////////////////////
		//Define Local variables for the simulation
		int nbrSample = context.getNumberOfSample();
		int nbrMarketPrice = context.getNumberOfMarketPrice();
		FinancialProduct financialProduct = context.getFinancialProduct();
		
		//Create the path generator for the simulation 
		MultiPathGenerator mpg = new MultiPathGenerator(context.volatilityIsDynamic());
		
		//Initialize the vector of MarketPricePath
		Vector<MarketPricePath> marketPricePaths = new Vector<MarketPricePath>(nbrSample);
		
		for(int i=0; i<nbrSample; ++i)
			marketPricePaths.setElementAt(new MarketPricePath(context.getMarketPriceVector()), i);
		
		//Initialize the random Vector
		Vector<Vector<Float> > randomVector = new Vector<Vector<Float> >(); //TODO
		
		

		Date today = context.getCurrentDay();
		
		//MainLoop
		while(financialProduct.getEndDate().isGreaterThan(today))
		{
			//Generate the today closingPrice for each path with correlation
			mpg.nextPathFor(marketPricePaths, randomVector);
		}
		
				
	}
	
	
}
