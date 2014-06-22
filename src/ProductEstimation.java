
public class ProductEstimation
{
	public static void main(String[] args)
	{
		Parser parser = new Parser();
		parser.setInputFileName(args[0]);
		Context context = new Context(parser);
		
		context.displayMarketPrices();
	}
}
