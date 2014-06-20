import java.util.Vector;


public class MarketPrice {
	
//	private MarketPrice correlatedSharePrice;
//	protected float driftRate;
	protected float volatility;
//	protected float correlation;
	protected float currentPrice;
	private String name;
	private Vector<Integer> weeklyClosedDays; // int or Date?
	private Vector<Date> holidays;
	private Vector<Float> historicalData;
	
	public MarketPrice(String name) {
		this.name = name;
	}
	
	public void setVolatility(float volatility) {
		this.volatility = volatility;
	}
	
	public Vector<Float> getHitoricalData() {
		return this.historicalData;
	}
	
	public void getNextPath(){
		
	}
	
}


