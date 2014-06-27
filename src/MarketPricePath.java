import java.util.Vector;

//This class gives the abstract of Sample to a group of market price.
//We'll make this sample evolutes using Monte Carlo

public class MarketPricePath
{
	private Vector<MarketPrice> marketPriceGroup;
	
	public MarketPricePath(Vector<MarketPrice> mpg)
	{
		marketPriceGroup = new Vector<MarketPrice>(mpg);
	}
	
	public boolean hasClosingPrice(Date today)
	{
		boolean result = false;
		for(int i=0; i<marketPriceGroup.size(); ++i)
		{
			if(marketPriceGroup.get(i).hasClosingPrice(today))
				result = true;
		}
		
		return result;
	}
	
	
}
