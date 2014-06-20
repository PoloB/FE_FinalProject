import java.util.Vector;


public class MarketPrice {
	
//	private MarketPrice correlatedSharePrice;
//	protected float driftRate;
	protected float volatility;
//	protected float correlation;
	protected float currentPrice;
	private String name;
	private Vector<Integer> weeklyClosedDays;
	private Vector<Date> holidays;
	private Vector<Float> historicalData;
	
	public MarketPrice(String n) {
		name = n;
	}
	
//	public void setVolatility(float volatility) {
//		this.volatility = volatility;
//	}
//	
//	public Vector<Float> getHitoricalData() {
//		return this.historicalData;
//	}
	
	public void setCurrentPrice(float cP) {
		currentPrice = cP;
	}
	
	public float getCurrentPrice() {
		return currentPrice;
	}
	
	public void getNextPath(){
		
	}
	
}


