import java.util.Vector;

//import umontreal.iro.lecuyer.rng.MRG32k3a;
//import umontreal.iro.lecuyer.stochprocess.GeometricBrownianMotion;


public class MultiPathGenerator
{
	
//	private Vector<Float> NormallyDistributedRandomNumbers;
	VolatilityCalculator volatilityCalculator;
	CorrelationCalculator correlationCalculator;
	
	
	public MultiPathGenerator(boolean isDynamic)
	{		
		float mu = 0.01f;
		
		if (!isDynamic)
		{
			volatilityCalculator = new StaticVolatilityCalculator();
		}
		
		else
			volatilityCalculator = new DynamicVolatilityCalculator();
	}
	
	
	public void nextPathFor(Vector<MarketPricePath> marketSamples, Vector<Vector<Float> > randomVector)
	{	
		for(int i=0; i < marketSamples.size(); ++i)
		{
			MarketPricePath currentEvaluatedMarketPath = marketSamples.get(i); 
			
			//Calculate the correlation in the sample
		}
		
	}
}
