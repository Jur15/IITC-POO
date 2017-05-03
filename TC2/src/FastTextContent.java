import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

public class FastTextContent
{
	private JFrame MainFrame;
	private JPanel ComponentsPanel;
	private JScrollPane ComponentsScroll;
	private JTextField TextField;
	private JButton SearchButton;
	
	public FastTextContent()
	{
		try
		{
			initializeFrame();
			MainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeFrame()
	{
		MainFrame = new JFrame("II Tarea Corta - POO");
		MainFrame.setBounds(100, 100, 800, 600);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{285, 71};
		gridBagLayout.rowHeights = new int[]{30, 126, 0};
		gridBagLayout.columnWeights = new double[]{1.0,0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		MainFrame.getContentPane().setLayout(gridBagLayout);
		
		TextField = new JTextField();
		TextField.setToolTipText("Inserte las palabras a buscar");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		MainFrame.getContentPane().add(TextField, gbc_textField);
		TextField.setColumns(1);
		
		SearchButton = new JButton("Buscar");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				ComponentsPanel.removeAll();
				searchWords();
			}
		});
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 0;
		MainFrame.getContentPane().add(SearchButton, gbc_searchButton);
		
		ComponentsPanel = new JPanel();
		ComponentsPanel.setLayout(new BoxLayout(ComponentsPanel, BoxLayout.Y_AXIS));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		
		ComponentsScroll = new JScrollPane(ComponentsPanel);
		ComponentsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ComponentsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		MainFrame.getContentPane().add(ComponentsScroll, gbc_panel);
	}
	
	private void searchWords()
	{
		String searchText = TextField.getText();
		String[] wordList = searchText.split(" ");
		for (String word : wordList)
		{
			WordSearcher searcher = new WordSearcher(ComponentsPanel,word);
			new Thread(searcher).start();
		}
	}
	
	public static void main(String[] args)
	{
		FastTextContent program = new FastTextContent();
	}

}