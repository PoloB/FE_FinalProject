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
		String[] ss = configFile.getLine(3).split(" ");
		this.numberSample = Integer.parseInt(ss[2]);
		
		ss = configFile.getLine(4).split(" ");
		this.resultStep = Integer.parseInt(ss[2]);
		
		ss = configFile.getLine(5).split("[ /]");
		this.currentDay = new Date(
									Integer.parseInt(ss[2]),
									Integer.parseInt(ss[3]),
									Integer.parseInt(ss[4])
								  );
		
		ss = configFile.getLine(0).split("[ ,]");
		this.marketPrices = new Vector<MarketPrice>();
		marketPrices.addElement(new MarketPrice(ss[2]));
		marketPrices.addElement(new MarketPrice(ss[3]));
	}

	public void setFinancialProduct(FinancialProduct financialProduct){
		this.financialProduct=financialProduct;
	}
	
}
