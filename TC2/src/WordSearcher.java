import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JLabel;

public class WordSearcher implements Runnable
{
	private int SearchTime;
	private String WordSearched;
	
	private JPanel ComponentPanel;
	private JFXPanel FXPanel = new JFXPanel();
	private WebView WebViewer;
	private JLabel WordLabel;
	private JLabel TimeLabel;
	
	public WordSearcher(JPanel pPanel,String pWord)
	{
		ComponentPanel = pPanel;
		WordSearched = pWord;
	}
	
	public void run()
	{
		String htmlCode = obtainHTML();
		createComponents(htmlCode);
	}
	
	private String obtainHTML()
	{
		PreviousSearches prevResults = PreviousSearches.getInstance();
		Chronometer timer = new Chronometer();
		String htmlCode;
		if (prevResults.getResults().containsKey(WordSearched))
		{
			new Thread(timer).start();
			htmlCode = prevResults.getResults().get(WordSearched);
			SearchTime = timer.getTime();
		}
		else
		{
			new Thread(timer).start();
			HTTPGetter requester = new HTTPGetter();
			htmlCode = requester.getRequest(WordSearched);
			SearchTime = timer.getTime();
			prevResults.addResult(WordSearched, htmlCode);
		}
		return htmlCode;
	}
	
	private void createComponents(String pHTMLCode)
	{
		Platform.runLater
		(
			() ->
			{
				WebViewer = new WebView();
				WebViewer.getEngine().loadContent(pHTMLCode);
				FXPanel.setScene(new Scene(WebViewer));
			}
		);
		
		WordLabel = new JLabel("Word: "+WordSearched);
		TimeLabel = new JLabel("Search Time: "+SearchTime);
		
		ComponentPanel.add(WordLabel);
		ComponentPanel.add(TimeLabel);
		ComponentPanel.add(FXPanel);
		
		ComponentPanel.setVisible(false);
		ComponentPanel.setVisible(true);
	}
}
