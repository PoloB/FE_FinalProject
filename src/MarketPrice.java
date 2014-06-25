import java.util.Vector;


public class MarketPrice
{
	private float volatility;
	private float initialPrice;
	private float currentPrice;
	private Date startingDay;
	private String name;
	private Vector<Integer> weeklyClosedDays = new Vector<Integer>();
	private Vector<Date> holidays = new Vector<Date>();
	private Vector<Float> historicalData;
	
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
		
		int i=0;
		
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
				historicalData.add(0.f);
			
			evaluatedDay.previous();
		}
		
		//Calculate First Volatility
		Vector<Float> historicalDataManipulated = new Vector<Float>();
		
		for(int j=0; j<historicalData.size()-1; j++)
			historicalDataManipulated.addElement ( (float)Math.log( historicalData.elementAt(j+1) / (historicalData.elementAt(j) )));
		
		/* mean of u(i) */
		float mean = 0.f;
		
		for(int j=0; j<historicalDataManipulated.size(); j++)
			mean += historicalDataManipulated.elementAt(j);
		
		mean /= historicalDataManipulated.size();
		/* volatility of u(i) */
		
		for (int j=0; j<historicalDataManipulated.size(); j++)
			volatility += Math.pow(historicalDataManipulated.elementAt(j)-mean,2);
		
		volatility = (float)Math.sqrt(volatility/historicalDataManipulated.size()-1);
	}

	//Getters
	public String getName() { return name; }
	public float getVolatility() { return volatility; }
	public Vector<Float> getHistoricalData() { return historicalData; }
	public float getInitialPrice() { return initialPrice; }
	public float getCurrentPrice() { return currentPrice; }
	
	//Setters
	public void setCurrentPrice(float cP) { currentPrice = cP; }
	public void setVolatility(float v) { volatility = v; }	

	boolean hasClosingPrice(Date today)
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


