
public class ProductEstimation
{
	public static void main(String[] args)
	{
		Parser parser = new Parser();
		parser.setInputFileName(args[0]);
		
		int i=0;
		String chain;
		while((chain=parser.getLine(i)) != null)
		{
			String[] res = chain.split(" ");
			System.out.println(res[2]);
			i++;
		}
		
	}
}
