import java.util.Vector;
import java.util.Random;


public class RandomStdVector extends RandomVector
{
	RandomStdVector(){}
	
	public Vector<Vector<Float>> getNew(int nbrSample, int nbrMarketPrice)
	{
		Random rand = new Random();
		
		
		Vector<Vector<Float> > result = new Vector<Vector<Float> >();
		for(int i=0; i<nbrSample; ++i)
		{
			Vector<Float> temp = new Vector<Float>();
			for(int j=0; j< nbrMarketPrice; ++j)
				temp.add(rand.nextFloat());
			
			result.add(temp);
		}
		
		return result;
	}
}
