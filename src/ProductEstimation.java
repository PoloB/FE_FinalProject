
public class ProductEstimation
{
	static MCSimulator MC;
	
	public static void main(String[] args)
	{
		Parser parser = new Parser();
		parser.setInputFileName(args[0]);
		
		//Get the MC method
		String mcs = parser.getLine(6).split(" ")[2];
		
		if(mcs == "MC")
			MC = new StdMCSimulator();
		
		if(mcs == "QMC"){}
			//TODO
			
		if(mcs == "RQMC"){}
			//TODO
		
		Context.get();
		
		MC.execute();
		
	}
}