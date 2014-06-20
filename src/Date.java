
public class Date
{
	
	public Date()
	{
		day = 1;
		month = 1;
		year = 2014;
	}
	
	private int calculateWeekDay(int d, int m, int y)
	{
		int week_day;
		
		int z;

		if(m<3)
		{
			z = y-1;
			week_day = (23*m)/9 + d + 4 + y + z/4 - z/100 + z/400;
		}
		else
		{
			z = y;
			week_day = (23*m)/9 + d + 4 + y + z/4 - z/100 + z/400 - 2;
		}
		
		return (week_day+6)%7;
	}
	
	public Date(int d, int m, int y)
	{
		day = d;
		month = m;
		year = y;
		weekDay = calculateWeekDay(d, m, y);
	}
	
	public int getWeekDay()
	{
		return weekDay;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setDay(int d)
	{
		day = d;
		weekDay = calculateWeekDay(d, month, year);
	}
	
	public void setMonth(int m)
	{
		month = m;
		weekDay = calculateWeekDay(day, m, year);
	}
	
	public void setYear(int y)
	{
		year = y;
		weekDay = calculateWeekDay(day, month, y);
	}
	
	public Date next()
	{
		Date result = new Date(day, month, year);
		
		//Check 
		
		//Check for leap year
		boolean isLeap = ((year%4 == 0 && year%100 != 0) || year%400 == 0);
		int monthSize;
		
		//Get the number of days in the month
		if(month==1 || month==3 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
		{
			monthSize = 31;
		}
		else if(month == 2)
		{
			if(isLeap)
				monthSize = 29;
			else
				monthSize = 28;
		}
		else
			monthSize = 30;
		
		if(day == monthSize && month != 12)
		{
			//We go on a new month on the same year
			day = 1;
			month++;
		}
		else if(day == monthSize && month == 12)
		{
			//Happy New Year!!!
			day = 1;
			month = 1;
			year++;
		}
		else
		{
			day++;
		}
		
		weekDay = (weekDay+1)%7;
		
		return result;
	}
	
	void display()
	{
		System.out.println(weekDay + " " + day + "/" + month + "/" + year);
	}
	
	private int day;
	private int month;
	private int year;
	private int weekDay;

}
