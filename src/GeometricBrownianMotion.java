import java.util.Random;
import java.util.Vector;
import java.util.Random;

public class GeometricBrownianMotion {

	private Vector<MarketPrice> marketPrices;
	
//	public GeometricBrownianMotion(Vector<MarketPrice> mP) {
//		marketPrices = mP;
//	}
	
	public GeometricBrownianMotion() {
		
	}
	
	public void next() {
//		MarketPrice m1 = mP.get(0);
//		MarketPrice m2 = mP.get(1);
		
		Random rand = new Random();
		
		Vector<Float> randoms = new Vector<Float>();
		for (int i=0; i<marketPrices.size(); ++i)
		{
			randoms.addElement(rand.nextFloat());
		}
		
		float cor = calculateCorrelation();
		randoms.elementAt(1) = cor*randoms.elementAt(0) 
				+ (float)(Math.sqrt(1-Math.pow((double)cor, 2)))*randoms.elementAt(1);
		
		for (int i=0; i<marketPrices.size(); ++i)
			marketPrices.get(i).setCurrentPrice(
						marketPrices.get(i).getCurrentPrice()
											   );
		
	}
	
	public float calculateCorrelation() {
		float cor = 0.f;
		return cor;
	}
	
}
