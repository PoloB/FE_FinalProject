import java.util.Vector;

public class Result
{
	Result()
	{
		profits = new Vector<Float>();
	}
	
	public void addProfit(float p)
	{
		profits.addElement(p);
	}
	
	public Vector<Float> getProfits()
	{
		return profits;
	}
	
	public float mean()
	{
		float m = 0.f;
		if(profits.size()>0)
		{
			for (int i=0; i<profits.size(); ++i)
			{
				m += profits.get(i);
			}
			m /= (float)profits.size();
		}
		return m; 
	}
	
	public float variance()
	{
		float v = 0.f;
		float m = mean();
		if(profits.size()>1)
		{
			for (int i=0; i<profits.size(); ++i)
			{
				v += Math.pow(profits.get(i)-m,2);
			}
			
			v /= (float)(profits.size()-1);
		}
		if(Float.isNaN(v))
		{
			System.out.println("Oups");
		}
		return v;
	}
	
	void display()
	{
		System.out.println("Mean: " + mean());
		System.out.println("Variance: " + variance());
	}
	
	private Vector<Float> profits;
	
}
