import java.util.Random;
import java.util.Vector;

import umontreal.iro.lecuyer.hups.HaltonSequence;


public class RandomLDVector extends RandomVector
{
	RandomLDVector(){}
	
	public Vector<Vector<Float> > getNew(int nbrSample, int nbrMarketPrice)
	{
		HaltonSequence haltonSequence = new HaltonSequence(10);
		haltonSequence.addFaureLemieuxPermutations();
		
		Vector<Vector<Float> > result = new Vector<Vector<Float> >();
		for(int i=0; i<nbrSample; ++i)
		{
			Vector<Float> temp = new Vector<Float>();
			for(int j=0; j< nbrMarketPrice; ++j)
			{
				temp.add((float) haltonSequence.getCoordinate(i*nbrMarketPrice+j+1,5));
			}
			
			result.add(temp);
		}
		
		return result;
	}
}
