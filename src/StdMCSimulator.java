import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Vector;


public class StdMCSimulator extends MCSimulator
{
	public StdMCSimulator()
	{
		randomVector = new RandomStdVector();
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
		
		//TODO
		/////////////////
		int subStepNumber = 10000;
		/////////////////
		
		//Create the path generator for the simulation 
		MultiPathGenerator mpg = new MultiPathGenerator(context.volatilityIsDynamic());
		
		//Initialize the vector of MarketPricePath
		Vector<MarketPricePath> marketPricePaths = new Vector<MarketPricePath>();
		
		for(int i=0; i<nbrSample; ++i)
		{
			marketPricePaths.add(new MarketPricePath(context.getMarketPriceVector()));
		}
		
		//Starting day of the simulation
		Date today = context.getCurrentDay();
		
		try {
			PrintWriter writer = new PrintWriter("test.csv", "UTF-8");
			
			writer.println("Date; S&P; Nikei");
			
			///////////
			//Main Loop
			///////////
			while(financialProduct.getEndDate().isGreaterThan(today))
			{
				//Generate the today closingPrice for each market
				mpg.nextPathFor(marketPricePaths, randomVector.getNew(nbrSample*subStepNumber, nbrMarketPrice));
				
				//Debug
				writer.println(today.toString() + "; " + marketPricePaths.get(0).getMarketPrices().get(0).getCurrentPrice() + "; " + marketPricePaths.get(0).getMarketPrices().get(1).getCurrentPrice()); 
				today.next();
			}
			
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
	}
	
	
}
