import java.util.Vector;


public class MarketPrice
{
	private float mean;
	private float volatility;
	private float currentPrice;
	private Date startingDay;
	private String name;
	private Vector<Integer> weeklyClosedDays = new Vector<Integer>();
	private Vector<Date> holidays = new Vector<Date>();
	private Vector<Float> historicalData;
    private Vector<Float> historicalReturn;
    private int numberOfClosingDayPerYear;
	
	public MarketPrice(String marketName, Date today, int historicalDataSize)
	{
		// Create a parser to get the information about the market Price
		Parser p = new Parser();
		p.setInputFileName(marketName + ".txt");
		name = marketName;
		
		
		//Weekly closed days
		String[] ss1 = p.getLine(1).split(" ");
		String s = ss1[2];
		ss1 = s.split("[,;]");
		int nbrWCdays = ss1.length;
		
		for(int i=0; i<nbrWCdays; ++i)
			weeklyClosedDays.add(Integer.parseInt(ss1[i]));
		
		//Holydays
		ss1 = p.getLine(2).split(" ");
		s = ss1[2];
		ss1 = s.split("[,;]");
		nbrWCdays = ss1.length;
		
		for(int i=0; i<nbrWCdays; ++i)
			holidays.add(new Date(ss1[i]));
		
		
		//Set historical data
		historicalData = new Vector<Float>();
		p.setInputFileName(marketName + ".csv");
		Date evaluatedDay = new Date(today);
		Date endEvaluation = new Date(today);
		int newMonth = (endEvaluation.getMonth() - historicalDataSize+12)%12;
		
		int t= endEvaluation.getMonth() - historicalDataSize;
		int newYear = endEvaluation.getYear();
		
		if(t <= 0)
			newYear -= Math.abs(endEvaluation.getMonth() - historicalDataSize)/12 +1;
		
		endEvaluation.setMonth(newMonth);
		endEvaluation.setYear(newYear);
		Date dateInFile = new Date();
		
		//Set the current price
		ss1 = p.getLine(0).split(";");
		currentPrice = Float.parseFloat(ss1[1]);
		
		
		int i=1;
		while(evaluatedDay.isGreaterThan(endEvaluation))
		{
			//Get the current line in the csv file
			ss1 = p.getLine(i).split(";");
			dateInFile = Date.toDate(ss1[0]);
			
			if(evaluatedDay.isEqualTo(dateInFile))
			{
				historicalData.add(Float.parseFloat(ss1[1]));
				i++;
			}
			else
				historicalData.add(-1.f);
			
			evaluatedDay.previous();
		}
		
		
		//Calculate First Volatility
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		
		for(int j=0; j<historicalData.size()-1; j++)
			historicalDataManipulated.addElement ( (float)Math.log( historicalData.elementAt(j+1) / (historicalData.elementAt(j) )));
	}

	//Getters
	public float getCurrentPrice() { return currentPrice; }
	public String getName() { return name; }
	public float getVolatility() { return volatility; }
	public Vector<Float> getHistoricalData() { return historicalData; }
	public float getHistoricalDataMean() { return mean; }
	public Vector<Float> getHistoricalReturn() { return historicalReturn; }
	
	//Setters
	public void setCurrentPrice(float cP) { currentPrice = cP; }
	public void setVolatility(float v) { volatility = v; }
	public void setHistoricalReturn(Vector<Float> hR) { historicalReturn = hR; }
	public void setHistoricalDataMean(float m) { mean = m; }
	
	//Miscenallous
	public boolean hasClosingPrice(Date today)
	{
		boolean result = true;
		
		for(int i=0; i<holidays.size(); ++i)
		{
			if(today.isEqualTo(holidays.get(i)))
				result = false;
		}
		
		for(int i=0; i<weeklyClosedDays.size(); ++i)
		{
			if(today.getWeekDay() == weeklyClosedDays.get(i))
				result = false;
		}
		
		return result;
	}
}


