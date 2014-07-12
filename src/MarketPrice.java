import java.util.Vector;


public class MarketPrice
{
	private float volatility;
	private float currentPrice;
	//private Date startingDay;
	private Vector<Integer> weeklyClosedDays = new Vector<Integer>();
	private Vector<Date> holidays = new Vector<Date>();
	private Vector<Float> historicalData = new Vector<Float>();
    private int numberOfClosingDayPerYear;
    private float mu;
	
    public MarketPrice()
    {
    	
    }
    
	public MarketPrice(String marketName, Date today, int historicalDataSize)
	{
		// Create a parser to get the information about the market Price
		Parser p = new Parser();
		p.setInputFileName(marketName + ".txt");
		
		
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
		
		
		//Calculate the number of opened days in one year
		i=1;
		Date evaluatedDay1 = new Date(today);
		Date oneYearBefore = new Date(evaluatedDay1);
		oneYearBefore.setYear(evaluatedDay1.getYear()-1);
		
		while(!evaluatedDay1.isEqualTo(oneYearBefore))
		{
			//Get the current line in the csv file
			ss1 = p.getLine(i).split(";");
			dateInFile = Date.toDate(ss1[0]);
			
			if(evaluatedDay1.isEqualTo(dateInFile))
			{
				i++;
			}
			
			evaluatedDay1.previous();
		}
		
		numberOfClosingDayPerYear = i;
		System.out.println(numberOfClosingDayPerYear);
		
		//Calculate First volatility
		
		// Mean of u(i)
		float mean = 0.f;
		
		for (int j=0; j<historicalData.size()-1; j++)
		{
			float currentPricet = historicalData.get(j);
			
			if(currentPricet>=0.f)
			{
				int cpt1=1;
				float previousPrice = historicalData.get(j+cpt1);
				
				while(previousPrice <=0.f && j+cpt1 < historicalData.size()-1)
				{
					cpt1++;
					previousPrice = historicalData.get(j+cpt1);
				}
				
				if(cpt1+j != historicalData.size()-1)
					mean += Math.log( currentPricet / previousPrice );
			}
		}
		
		mean /= (numberOfClosingDayPerYear*historicalDataSize/12.f);
		
		mu = mean;
		
		// Volatility of u(i)
		for (int j=0; j<historicalData.size()-1; j++)
		{
			float currentPricet = historicalData.get(j);
			
			if(currentPricet>=0.f)
			{
				int cpt1=1;
				float previousPrice = historicalData.get(j+cpt1);
				
				while(previousPrice <=0.f && j+cpt1 < historicalData.size()-1)
				{
					cpt1++;
					previousPrice = historicalData.get(j+cpt1);
				}
				
				if(cpt1+j != historicalData.size()-1)
					volatility += Math.pow(Math.log( currentPricet / previousPrice ) - mean,2);
			}
		}
		
		volatility = (float) (Math.sqrt(volatility / ((numberOfClosingDayPerYear*historicalDataSize/12.f-1.f))) * Math.sqrt(numberOfClosingDayPerYear*historicalDataSize));
	}

	public MarketPrice(MarketPrice mp)
	{
		volatility = mp.volatility;
		currentPrice = mp.currentPrice;
		//startingDay = mp.startingDay;
		weeklyClosedDays = new Vector<Integer>(mp.weeklyClosedDays);
		holidays = new Vector<Date>(mp.holidays);
		historicalData = new Vector<Float>(mp.historicalData); 
	    numberOfClosingDayPerYear = mp.numberOfClosingDayPerYear;
	    mu = mp.mu;
	}

	//Getters
	public float getCurrentPrice() { return currentPrice; }
	public float getVolatility() { return volatility; }
	public Vector<Float> getHistoricalData() { return historicalData; }
	public int getNumberOfClosingDayPerYear() { return numberOfClosingDayPerYear; }
	public Vector<Integer> getWeeklyClosedDays() { return weeklyClosedDays; }
	public Vector<Date> getHolydays() { return holidays; }
	public float getMu() { return mu; }
	
	//Setters
	public void setCurrentPrice(float cP) { currentPrice = cP; }
	public void setVolatility(float v) { volatility = v; }
	public void setMu(float m) { mu = m; }
	public void setNumberOfClosingDaysPerYear(int nocdpy) {numberOfClosingDayPerYear = nocdpy; }
	
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
	
	public MarketPrice clone()
	{
		MarketPrice copy = new MarketPrice();
		
		//Copy the historical data
		Vector<Float> hdCopy = copy.getHistoricalData();
		for(int i=0; i< historicalData.size(); ++i)
			hdCopy.add(historicalData.get(i).floatValue());
		
		//Copy the weekly closed days
		Vector<Integer> wcdCopy = copy.getWeeklyClosedDays();
		for(int i=0; i< weeklyClosedDays.size(); ++i)
			wcdCopy.add(weeklyClosedDays.get(i).intValue());
		
		//Copy the holydays
		Vector<Date> hCopy = copy.getHolydays();
		for(int i=0; i< holidays.size(); ++i)
			hCopy.add(holidays.get(i).clone());
		
		copy.setCurrentPrice(currentPrice);
		copy.setVolatility(volatility);
		copy.setNumberOfClosingDaysPerYear(numberOfClosingDayPerYear);
		copy.setMu(mu);
		
		return copy;
	}
}


