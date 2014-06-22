import java.util.Vector;


public class MarketPrice {
	
	public MarketPrice(String n,Date sD) {
		name = n;
		startingDay = sD;
//		initialPrice = ;
		this.createWeeklyClosedDays();
		this.createHolidays();
	}
	
	public void createWeeklyClosedDays() {
		weeklyClosedDays.addElement(5); /*Saturday*/
		weeklyClosedDays.addElement(6); /*Sunday*/
	}
	
	public void createHolidays() {
		if (name=="SandP500")
		{
			int[] day = {1,20,17,18,26,4,1,27,24,25};
			int[] month = {1,1,2,4,5,7,9,11,12,12};
			int year = 2014;
			this.calculateHolidays(day, month, year);
//			holidays.addElement(new Date(1,1,2014));
//			holidays.addElement(new Date(20,1,2014));
//			holidays.addElement(new Date(17,2,2014));
//			holidays.addElement(new Date(18,4,2014));
//			holidays.addElement(new Date(26,5,2014));
//			holidays.addElement(new Date(4,7,2014));
//			holidays.addElement(new Date(1,9,2014));
//			holidays.addElement(new Date(27,11,2014));
//			holidays.addElement(new Date(24,12,2014));
//			holidays.addElement(new Date(25,12,2014));
		}
		else if (name=="Nikkei225")
		{
			
		}
		else if (name=="EuroStoxx50")
		{
			
		}
			
	}
	
	public void calculateHolidays(int[] d,int[] m, int y)
	{
//		Date day = startingDay;
		int j = 0;
		while(m[j] < startingDay.getMonth())
		{
			j++;
		}
		
//		while()
		for (int i=j; i< d.length; i++)
		{
			
//			while 
		}
	}
	
	public void createHistoricalData() {
		
	}
	
	public void setVolatility(float v) {
		volatility = v;
	}
	
	public Vector<Float> getHitoricalData() {
		return this.historicalData;
	}
	
	public void setCurrentPrice(float cP) {
		currentPrice = cP;
	}
	
	public float getInitialPrice() {
		return initialPrice;
	}
	
	public float getCurrentPrice() {
		return currentPrice;
	}
	
	public void getNextPath(){
		
	}
	
	private float volatility;
	private float initialPrice;
	private float currentPrice;
	private Date startingDay;
	private String name = new String();
	private Vector<Integer> weeklyClosedDays = new Vector<Integer>();
	private Vector<Date> holidays = new Vector<Date>();
	private Vector<Float> historicalData;
	
}


