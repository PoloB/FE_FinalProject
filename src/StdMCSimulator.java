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
		FinancialProduct financialProductRef = context.getFinancialProduct();
		
		//Create the vector of financial product samples
		Vector<FinancialProductSample> financialProducts = new Vector<FinancialProductSample>();
		for(int k=0; k<nbrSample; ++k)
			financialProducts.add(new FinancialProductSample(financialProductRef));
		
		//Time step
		int subStepNumber = 1;	//Set to have 1 evaluation of the market prices every minute
		
		//Create the path generator for the simulation
		MultiPathGenerator mpg = new MultiPathGenerator(context.volatilityIsDynamic());
		
		//Initialize the vector of MarketPricePath
		Vector<MarketPricePath> marketPricePaths = new Vector<MarketPricePath>();
		for(int i=0; i<nbrSample; ++i)
		{
			//Make a copy of the market price vector which is defined in the context
			Vector<MarketPrice> copy = new Vector<MarketPrice>();
			Vector<MarketPrice> toCopy = context.getMarketPriceVector();
			
			for(int k=0; k<toCopy.size();++k)
				copy.add(toCopy.get(k).clone());
			
			marketPricePaths.add(new MarketPricePath(copy));
		}
		
		//Starting day of the simulation
		Date today = context.getCurrentDay();
		
		
		try {
			PrintWriter writer = new PrintWriter("test.csv", "UTF-8");
			
			writer.println("Date; S&P1; Nikei1; S&P2; Nikei2");
			
			///////////
			//Main Loop
			///////////
			while(financialProductRef.getEndDate().isGreaterThan(today))
			{
				//Generate the today closingPrice for each market
				mpg.nextPathFor(marketPricePaths, randomVector.getNew(nbrSample*subStepNumber, nbrMarketPrice));
				
				//Evaluate return of the financial products
				for(int k=0; k<nbrSample; ++k)
				{
					financialProducts.get(k).evaluateReturn(marketPricePaths.get(k).getMarketPrices());
				}
				
				//Debug
				//writer.println(today.toString() + "; " + marketPricePaths.get(0).getMarketPrices().get(0).getCurrentPrice() + "; " + marketPricePaths.get(1).getMarketPrices().get(0).getCurrentPrice());
				today.next();
			}
			
			for(int k=0; k<nbrSample; ++k)
			{
				financialProducts.get(k).endingReturn(marketPricePaths.get(k).getMarketPrices());
				writer.println(financialProducts.get(k).getGain());
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
