import java.util.Random;
import java.util.Vector;

import umontreal.iro.lecuyer.rng.MRG32k3a;
import umontreal.iro.lecuyer.stochprocess.GeometricBrownianMotion;


public class PathGenerator
{
	private Vector<GeometricBrownianMotion> gbmGenerator;
	
	public PathGenerator(int numberOfMarketPrice)
	{
		gbmGenerator = new Vector<GeometricBrownianMotion>();
		
		for(int i=0; i< numberOfMarketPrice; ++i)
		{
			//TO DO
			//Calculate the first mu and sigma
			float mu = 1.0f; //TO DO
			float sigma = 1.0f; //TO DO
			
			gbmGenerator.add(new GeometricBrownianMotion(100, mu, sigma, new MRG32k3a()));
		}
	}
	
	
	
	public void next(Vector<MarketPrice> marketPrices)
	{	
		/*Random rand = new Random();
		
		Vector<Float> randoms = new Vector<Float>();
		for (int i=0; i<marketPrices.size(); ++i)
		{
			randoms.addElement(rand.nextFloat());
		}
		
		float cor = calculateCorrelation();
		randoms.setElementAt(cor*randoms.elementAt(0) + (float)(Math.sqrt(1-Math.pow((double)cor, 2)))*randoms.elementAt(1), 1);
		
		for (int i=0; i<marketPrices.size(); ++i)
			marketPrices.get(i).setCurrentPrice(marketPrices.get(i).getCurrentPrice());*/
		
		
	}
	
	public float calculateCorrelation()
	{
		float cor = 0.f;
		return cor;
	}
	
}
