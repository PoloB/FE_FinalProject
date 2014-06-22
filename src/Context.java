import java.util.Vector;


public class Context
{
	private int numberSample;
	private int resultStep;
	private Date currentDay;
//	private MarketPrice riskFreeRate;
	private Vector<MarketPrice> marketPrices;
	private FinancialProduct financialProduct;

	public Context(Parser configFile)
	{
		marketPrices = new Vector<MarketPrice>();
		//Get the number of sample for the Monte Carlo simulation
		String[] ss = configFile.getLine(3).split(" ");
		numberSample = Integer.parseInt(ss[2]);
		
		//Get the result Step
		ss = configFile.getLine(4).split(" ");
		resultStep = Integer.parseInt(ss[2]);
		
		//Get the simulation starting day
		ss = configFile.getLine(5).split(" ");
		currentDay = new Date(ss[2]);
		
		//Get the historical data size
		ss = configFile.getLine(2).split(" ");
		int historicalDataSize = Integer.parseInt(ss[2]);
		System.out.println(historicalDataSize);
		
		//Get the list of the market prices for the simulation 
		ss = configFile.getLine(0).split(" ");
		String s = ss[2];
		ss = s.split("[,]");
		for(int i=0; i<ss.length; ++i)
		{
			MarketPrice mp = new MarketPrice(ss[i], currentDay, historicalDataSize);
			marketPrices.add(mp);
		}
	}

	public void setFinancialProduct(FinancialProduct fP)
	{
		financialProduct=fP;
	}
	
	public void displayMarketPrices()
	{
		for(int i=0; i < marketPrices.size(); ++i)
		{
			System.out.println(marketPrices.elementAt(i).getName());
			marketPrices.elementAt(i).displayHistoricalData();
		}
	}
	
}
