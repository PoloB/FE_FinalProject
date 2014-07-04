
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
	
	public Date(String date)
	{
		//String with the syntax dd/mm/yyyy
		String[] ss = date.split("/");
		day = Integer.parseInt(ss[0]);
		month = Integer.parseInt(ss[1]);
		year = Integer.parseInt(ss[2]);
	}
	
	public Date(Date d)
	{
		Date newDate = new Date();
		newDate.day = d.day;
		newDate.month = d.month;
		newDate.year = d.year;
		day = newDate.day;
		month = newDate.month;
		year = newDate.year;
	}
	
	public static Date toDate(String date)
	{
		Date result = new Date();
		String[] ss = date.split("/");
		result.day = Integer.parseInt(ss[0]);
		result.month = Integer.parseInt(ss[1]);
		result.year = Integer.parseInt(ss[2]);
		return result;
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
	
	public void set(Date date)
	{
		day = date.day;
		month = date.month;
		year = date.year;
	}
	
	public boolean isEqualTo(Date date)
	{
		if(year == date.year && month == date.month && day == date.day)
			return true;
		else
			return false;
	}
	
	public boolean isGreaterThan(Date date)
	{	
		boolean A = year > date.year;
		boolean Ap = year == date.year;
		
		boolean B = month > date.month;
		boolean Bp = month == date.month;
		
		boolean C = day > date.day;
		
		return A || (Ap && (B || (Bp && C)));
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
		
		weekDay = calculateWeekDay(day, month, year);
		
		return result;
	}
	
	public Date previous()
	{
		Date result = new Date(day, month, year);
		
		//Check 
		
		//Check for leap year
		boolean isLeap = ((year%4 == 0 && year%100 != 0) || year%400 == 0);
		int monthSize;
		
		//Get the number of days in the previous month
		int previousMonth = month-1;
		
		if(previousMonth == 0)
			previousMonth = 12;
		
		if(previousMonth==1 || previousMonth==3 || previousMonth==3 || previousMonth==5 || previousMonth==7 || previousMonth==8 || previousMonth==10 || previousMonth==12)
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
		
		if(day == 1 && month != 1)
		{
			//We go back on previous month on the same year
			day = monthSize;
			month--;
		}
		else if(day == 1 && month == 1)
		{
			//Happy New Year!!!
			day = 31;
			month = 12;
			year--;
		}
		else
		{
			day--;
		}
		
		weekDay = calculateWeekDay(day, month, year);
		
		return result;
	}
	
	public void display()
	{
		System.out.println(weekDay + " " + day + "/" + month + "/" + year);
	}
	
	public String toString()
	{
		return day + "/" + month + "/" + year;
	}
	
	public Date clone()
	{
		Date copy = new Date();
		
		copy.day = day;
		copy.month = month;
		copy.year = year;
		copy.weekDay = weekDay;
		
		return copy;
	}
	
	private int day;
	private int month;
	private int year;
	private int weekDay;

}
