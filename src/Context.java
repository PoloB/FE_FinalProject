import java.util.Vector;


public class Context
{
	//Singleton
	private static Context Instance = null; 
	
	private int numberSample;
	private int resultStep;
	private boolean dynamicVolatility;
	private Date currentDay;
//	private MarketPrice riskFreeRate;
	private Vector<MarketPrice> marketPrices;
	private FinancialProduct financialProduct;

	private Context()
	{
		Parser parser = new Parser();
		parser.setInputFileName("Data/config.txt");
		marketPrices = new Vector<MarketPrice>();
		
		//Get the number of sample for the Monte Carlo simulation
		String[] ss = parser.getLine(3).split(" ");
		numberSample = Integer.parseInt(ss[2]);
		
		//Get the result Step
		ss = parser.getLine(4).split(" ");
		resultStep = Integer.parseInt(ss[2]);
		
		//Get the simulation starting day
		ss = parser.getLine(5).split(" ");
		currentDay = new Date(ss[2]);
		
		//Get the historical data size
		ss = parser.getLine(2).split(" ");
		int historicalDataSize = Integer.parseInt(ss[2]);
		
		//Get the type of volatility Calculator
		ss = parser.getLine(1).split(" ");
		if(Integer.parseInt(ss[2]) == 1)
			dynamicVolatility = true;
		else
			dynamicVolatility = false;
		
		//Get the list of the market prices for the simulation 
		ss = parser.getLine(0).split(" ");
		String s = ss[2];
		ss = s.split("[,]");
		for(int i=0; i<ss.length; ++i)
		{
			MarketPrice mp = new MarketPrice(ss[i], currentDay, historicalDataSize);
			marketPrices.add(mp);
		}
		
		
		
		//Add the financial product
		financialProduct = new FinancialProductB(this);
		
	}
	
	public static Context get()
	{
		if(Instance == null)
			Instance = new Context();
		
		return Instance;
	}

	public void setFinancialProduct(FinancialProduct fP)
	{
		financialProduct=fP;
	}
	
	public void displayMarketPrices()
	{
		for(int i=0; i < marketPrices.size(); ++i)
			System.out.println(marketPrices.elementAt(i).getName());
	}
	
	public int getNumberOfSample()
	{
		return numberSample;
	}
	
	public int getNumberOfMarketPrice()
	{
		return marketPrices.size();
	}
	
	public MarketPrice getMarketPrice(int i)
	{
		return marketPrices.get(i);
	}
	
	public Vector<MarketPrice> getMarketPriceVector()
	{
		return marketPrices;
	}
	
	public FinancialProduct getFinancialProduct()
	{
		return financialProduct;
	}
	
	public Date getCurrentDay()
	{
		return currentDay;
	}
	
	boolean volatilityIsDynamic()
	{
		return dynamicVolatility;
	}
	
}
