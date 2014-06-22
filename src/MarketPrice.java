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
		{
			newYear -= Math.abs(endEvaluation.getMonth() - historicalDataSize)/12 +1;
		}
		
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
			{
				historicalData.add(0.f);
			}
				evaluatedDay.previous();
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
	
	public void createHistoricalData()
	{
		
	}
	
	public void setVolatility(float v)
	{
		volatility = v;
	}
	
	public Vector<Float> getHitoricalData()
	{
		return this.historicalData;
	}
	
	public void setCurrentPrice(float cP)
	{
		currentPrice = cP;
	}
	
	public float getInitialPrice()
	{
		return initialPrice;
	}
	
	public float getCurrentPrice()
	{
		return currentPrice;
	}
	
	public void getNextPath()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void displayHistoricalData()
	{
		for(int i=0; i < historicalData.size(); ++i)
		{
			System.out.println(historicalData.elementAt(i));
		}
	}
}


