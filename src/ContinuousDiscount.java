import java.util.Vector;

public class ContinuousDiscount {

	ContinuousDiscount() {
		
	}
	
	public void set(Date sD, Date eD,Vector<Float> hRFR) {
		startDay = sD;
		endDay = eD;
		historicalRiskFreeRate = hRFR;
	}
	
	public float calculate() {
		double value = 0.d;
		
		for (int i=0; i<historicalRiskFreeRate.size(); i++)
		{
			value += historicalRiskFreeRate.get(i);
		}
		value = Math.exp(-value); // (-value* Number of Opened Days between the two dates expressed in year)
		
		return (float)value;
	}
	
	private Date startDay;
	private Date endDay;
	private Vector<Float> historicalRiskFreeRate;
}
