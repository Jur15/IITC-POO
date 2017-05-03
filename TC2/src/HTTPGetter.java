import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetter
{
	private final String USER_AGENT = "Mozilla/5.0";
	
	public String getRequest(String pWord)
	{
		String webSourceCode = "";
		try
		{
			URL website = new URL("http://www.google.com/search?q="+pWord);
			HttpURLConnection connection = (HttpURLConnection) website.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Accept-Language", "UTF-8");
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer sourceCode = new StringBuffer();
	        while ((inputLine = input.readLine()) != null)
	        	sourceCode.append(inputLine);
	        input.close();
	        webSourceCode = sourceCode.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return webSourceCode;
	}
}