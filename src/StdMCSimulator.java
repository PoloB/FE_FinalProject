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
		
		//Get the number of samples
		int nbrSample = context.getNumberOfSample();
		int nbrMarketPrice = context.getNumberOfMarketPrice();
		
		//Create the volatility calculator
		
		FinancialProduct financialProduct = context.getFinancialProduct();
		
		//Generate a uniformly distributed sample vector
		Vector<Float> sVec = new Vector<Float> (nbrMarketPrice);
		
		Random rand = new Random();
		
		for(int j=0; j<nbrMarketPrice; ++j)
			sVec.setElementAt(rand.nextFloat(), j);
		
		//TO DO!!! Transform the uniformly distributed sample vector using a geometric brownian motion process
		
		
		
				
	}
	
}
