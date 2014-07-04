import java.util.Vector;


public class FinancialProductSample
{
	private boolean knockIn;
	private boolean knockOut;
	private float gain;
	private float interestRate;
	private int numberOfPaymentRealized;
	
	private FinancialProduct financialProductDefinition;
	
	public FinancialProductSample(FinancialProduct fp)
	{
		financialProductDefinition = fp;
		interestRate = fp.getInterestRate();
		knockIn = false;
		knockOut = false;
		gain = -1.f;
		numberOfPaymentRealized = 0;
	}
	
	public void evaluateReturn(Vector<MarketPrice> marketPrices)
	{
		if(!knockOut)
		{	
			for(int k=0; k<marketPrices.size(); ++k)
			{
				if(marketPrices.get(k).getCurrentPrice() < financialProductDefinition.getKnockInValue() * financialProductDefinition.getStartingMarketPrice(k))
					knockIn = true;
			}
			
			if(marketPrices.get(0).getCurrentPrice() > financialProductDefinition.getKnockOutValue() * financialProductDefinition.getStartingMarketPrice(0)
					&& marketPrices.get(1).getCurrentPrice() > financialProductDefinition.getKnockOutValue() * financialProductDefinition.getStartingMarketPrice(1))
			{
				knockOut = true;
				gain += 1 + interestRate / (float)financialProductDefinition.getNumberOfPaymentPerYear(); //Add discount
			}
			
			//Check if we have a return today
			boolean needToEvaluateReturn = false;
			for(int i=0; i<financialProductDefinition.getInterestPaymentDates().size(); ++i)
			{
				if(Context.get().getCurrentDay().isEqualTo(financialProductDefinition.getInterestPaymentDates().get(i)))
				{
					//We got our pay today!!!
					needToEvaluateReturn = true;
				}
			}
			
			if(needToEvaluateReturn)
			{
				//Let's calculate the return of our product
				gain += interestRate / (float)financialProductDefinition.getNumberOfPaymentPerYear(); //Add discount
				numberOfPaymentRealized++;
			}
		}
	}
		
	public boolean isOut()
	{
		return knockOut;
	}
	
	public void endingReturn(Vector<MarketPrice> marketPrices)
	{
		if(!knockOut)
		{
			if(!knockIn)
				gain += 1.f;
			else
			{
				float m1 = Math.min(marketPrices.get(0).getCurrentPrice() / financialProductDefinition.getStartingMarketPrice(0),
									marketPrices.get(1).getCurrentPrice() / financialProductDefinition.getStartingMarketPrice(1));
				float m2 = Math.min(m1, 1.f);
				
				gain += m2;
			}
		}
	}
	
	public float getGain()
	{
		return gain;
	}
}
