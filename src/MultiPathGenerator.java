import java.util.Random;
import java.util.Vector;

import umontreal.iro.lecuyer.rng.MRG32k3a;
import umontreal.iro.lecuyer.stochprocess.GeometricBrownianMotion;


public class MultiPathGenerator
{
//	private Vector<Float> NormallyDistributedRandomNumbers;
	Float mu;
	Vector<Vector<Float>> volatility;
	VolatilityCalculator volatilityCalculator;
	
	
	public MultiPathGenerator(Vector<Vector<MarketPrice>> marketPrices, boolean isDynamic)
	{
		float mu = 1.0f;
		
		if (!isDynamic)
			volatilityCalculator = new ConstantVolatilityCalculator();
		else 
			volatilityCalculator = new VariableVolatilityCalculator();
		
		volatility = new Vector<Vector<Float>>();
		Vector<Float> tmp = new Vector<Float>();
		float sigma;
		for (int j=0; j< marketPrices.size(); ++j)
		{
			volatilityCalculator.initialize(marketPrices.get(j).get(0));
			sigma = volatilityCalculator.getVolatility();
			for (int i=0; i< marketPrices.get(j).size(); ++i)
			{
				tmp.addElement(sigma);
			}
			volatility.addElement(tmp);
		}
	}
	
	
	public void next(Vector<MarketPrice> marketPrices)
	{	
		Random rand = new Random();
		
		Vector<Float> randoms = new Vector<Float>();
		for (int i=0; i<marketPrices.size(); ++i)
		{
			randoms.addElement(rand.nextFloat());
		}
		
		float cor = calculateCorrelation();
		randoms.setElementAt(cor*randoms.elementAt(0) + (float)(Math.sqrt(1-Math.pow((double)cor, 2)))*randoms.elementAt(1), 1);
		
		for (int i=0; i<marketPrices.size(); ++i)
			marketPrices.get(i).setCurrentPrice(marketPrices.get(i).getCurrentPrice());
		
	}
	
	public float calculateCorrelation() {
		float cor = 0.f;
		return cor;
	}
	
}
