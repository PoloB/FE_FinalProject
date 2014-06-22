import java.util.Vector;


public class VariableVolatilityCalculator extends VolatilityCalculator {

	VariableVolatilityCalculator(Vector<Float> historicalData) {
		volatility = 0.f;
		/* u(i) = (S(i)-S(i-1))/S(i-1)) */
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		for(int i=0; i<historicalData.size()-1; i++)
		{
			historicalDataManipulated.addElement(
					(historicalData.elementAt(i+1)-historicalData.elementAt(i))/historicalData.elementAt(i)
												);
		}
		/* Approximated volatility of u(i) */
		for (int i=0; i<historicalDataManipulated.size(); i++)
		{
			volatility += Math.pow(historicalDataManipulated.elementAt(i),2);
		}
		volatility = (float)Math.sqrt(volatility/historicalDataManipulated.size());
	}
	
	public void update(){}
}
