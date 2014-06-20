import java.io.*;

public class Parser
{
	private String fileName;
	
	Parser()
	{
		
	}
	
	public void setInputFileName(String file_name)
	{
		fileName = file_name;
	}
	
	public String getLine(int lineNumber)
	{	
		try
		{
			InputStream ips = new FileInputStream(fileName); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			int cpt = 0;
			
			while ((line=br.readLine())!=null && cpt < lineNumber)
			{
				cpt++;
			}
			
			br.close(); 
			
			if(line!=null)
				return line;
			else
				return null;
		}		
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
}
