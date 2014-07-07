
public class ProductEstimation
{
	public static void main(String[] args)
	{
		Parser parser = new Parser();
		parser.setInputFileName(args[0]);
		
		//Get the MC method
		String mcs = parser.getLine(6).split(" ")[2];
		RandomVector rdmVec;
		
		if(Integer.parseInt(mcs) == 1)
			rdmVec = new RandomLDVector();
		else
			rdmVec = new RandomStdVector();
		
		Context.get();
		MCSimulator MC = new MCSimulator(rdmVec);
		MC.execute();
		
	}
}