
public class Result
{
	Result()
	{
		profitAverage = 0.f;
		profitVariance = 0.f;
	}
	
	void setProfitAverage(float profit_average)
	{
		profitAverage = profit_average;
	}
	
	void setProfitVariance(float profit_variance)
	{
		profitVariance = profit_variance;
	}
	
	float getProfitAverage()
	{
		return profitAverage;
	}
	
	float getProfitVariance()
	{
		return profitVariance;
	}
	
	void display()
	{
		System.out.println("Mean: " + profitAverage);
		System.out.println("Variance" + profitVariance);
	}
	
	private float profitAverage;
	private float profitVariance;
	
}
