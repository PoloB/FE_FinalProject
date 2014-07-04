import java.util.Vector;


public class FinancialProductSample
{
	private boolean knockIn;
	private boolean knockOut;
	private float gain;
	private float interestRate;
	
	private FinancialProduct financialProductDefinition;
	
	FinancialProductSample(FinancialProduct fp)
	{
		financialProductDefinition = fp;
		interestRate = fp.getInterestRate();
		knockIn = false;
		knockOut = false;
		gain = 100.f;
	}
	
	void evaluateReturn(Vector<MarketPrice> marketPrices)
	{
		//Check if we need to modify the knockIn and knockOut
		boolean needToUpdateKnock = true;
		
		for(int k=0; k<marketPrices.size(); ++k)
		{
			if(marketPrices.get(k).getCurrentPrice() < financialProductDefinition.getKnockInValue() * 0.6)
				knockIn = true;
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
			gain = gain * (1.f + interestRate);
		}
	}
}
