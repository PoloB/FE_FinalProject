import java.util.Vector;


public class ConstantVolatilityCalculator extends VolatilityCalculator
{

	ConstantVolatilityCalculator(Vector<Float> historicalData)
	{
		volatility = 0.f;
		/* u(i) = ln(S(i)/S(i-1)) */
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		
		for(int i=0; i<historicalData.size()-1; i++)
			historicalDataManipulated.addElement ( (float)Math.log( historicalData.elementAt(i+1) / (historicalData.elementAt(i) )));
		
		/* mean of u(i) */
		float mean = 0.f;
		
		for(int i=0; i<historicalDataManipulated.size(); i++)
			mean += historicalDataManipulated.elementAt(i);
		
		mean /= historicalDataManipulated.size();
		/* volatility of u(i) */
		
		for (int i=0; i<historicalDataManipulated.size(); i++)
			volatility += Math.pow(historicalDataManipulated.elementAt(i)-mean,2);
		
		volatility = (float)Math.sqrt(volatility/historicalDataManipulated.size()-1);
	}
	
	public void update(){}
	
}
