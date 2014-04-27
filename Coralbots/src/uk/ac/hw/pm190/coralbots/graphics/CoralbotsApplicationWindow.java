package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CoralbotsApplicationWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private final CardLayout cardLayout = new CardLayout();
	private final JPanel contentPanel = new JPanel(cardLayout);
	private final JPanel startPanel = new JPanel(new BorderLayout());
	private final JPanel helpPanel = new JPanel(new BorderLayout());
	private SimulationConfigurationPanel simPanel;
	private SimulationResultsPanel resultsPanel;
	private JScrollPane scroll = new JScrollPane(contentPanel);

	public CoralbotsApplicationWindow()
	{
		contentPanel.add(startPanel, ApplicationPanel.START.toString());
		contentPanel.add(helpPanel, ApplicationPanel.HELP.toString());
		
		createStartPanel();
		createHelpPanel();
				
		add(scroll);
		add(contentPanel);
		setTitle("Coralbots Simulation Manager");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createStartPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		JButton help = new JButton("Help");
		help.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				showPanel(ApplicationPanel.HELP);
			}
		});
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(simPanel == null)
				{
					simPanel = new SimulationConfigurationPanel(CoralbotsApplicationWindow.this);
					contentPanel.add(simPanel, ApplicationPanel.SIMULATION.toString());
				}
				showPanel(ApplicationPanel.SIMULATION);
			}
		});
		buttonPanel.add(help);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(start);
		
		JPanel textPanel = new JPanel();
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText("Welcome to the Coralbots Simulation Manager\n"
				+ "To begin click Start, or for help click Help");
		textPanel.add(Box.createGlue());
		textPanel.add(text);
		textPanel.add(Box.createGlue());
		startPanel.add(textPanel, BorderLayout.CENTER);
		startPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void createHelpPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				showPanel(ApplicationPanel.START);
			}
		});
		buttonPanel.add(back);
		buttonPanel.add(Box.createHorizontalGlue());
		
		JPanel textPanel = new JPanel();
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText("To create a simulation you must supply the following parameters:\n"
				+ "\tWorld End Coordinates:\n"
				+ "\t\tThree dimensional coordinates that represent the world end.\n"
				+ "\t\tThe minimum coordinates are (9,9,9)\n"
				+ "\tNumber of Coralbots:\n"
				+ "\t\tThe number of coralbots in the simulation\n"
				+ "\tSimulation Cycles:\n"
				+ "\t\tThe number of cycles to run the simulation over\n"
				+ "\tAmount of Coral:\n"
				+ "\t\tThe number of coral blocks to be placed into the simulation randomly\n"
				+ "\tRules File\n"
				+ "\t\tThe file containing the selected rules for use by the coralbots");
		textPanel.add(Box.createGlue());
		textPanel.add(text);
		textPanel.add(Box.createGlue());
		helpPanel.add(textPanel, BorderLayout.CENTER);
		helpPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void showPanel(ApplicationPanel panel)
	{
		if(panel == ApplicationPanel.SIMULATION)
		{
			if(simPanel == null)
			{
				return;
			}
		}
		else if(panel == ApplicationPanel.RESULTS)
		{
			if(simPanel == null || resultsPanel == null)
			{
				return;
			}
		}
		cardLayout.show(contentPanel, panel.toString());
	}
	
	public void setResultsPanel(SimulationResultsPanel panel)
	{
		if(resultsPanel != null)
		{
			remove(resultsPanel);
			cardLayout.removeLayoutComponent(resultsPanel);
		}
		resultsPanel = panel;
		contentPanel.add(resultsPanel, ApplicationPanel.RESULTS.toString());
	}
}
