package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.RandomLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationConfigurationPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final CoralbotsApplicationWindow containerWindow;
	private final JTextArea errors = new JTextArea();
	
	public SimulationConfigurationPanel(CoralbotsApplicationWindow containerWindow)
	{
		this.containerWindow = containerWindow;
		JPanel endLocation = new JPanel();
		endLocation.setLayout(new BoxLayout(endLocation, BoxLayout.LINE_AXIS));
		final JTextField endX = new JTextField();
		final JTextField endY = new JTextField();
		final JTextField endZ = new JTextField();
		endLocation.add(endX);
		endLocation.add(endY);
		endLocation.add(endZ);

		final JTextField robots = new JTextField();
		final JTextField cycles = new JTextField();
		final JTextField corals = new JTextField();
		
		JPanel simOpts = new JPanel(new GridLayout(2,8));
		simOpts.add(new JLabel("End Loc:"));
		simOpts.add(endLocation);
		simOpts.add(new JLabel("Coralbots:"));
		simOpts.add(robots);
		simOpts.add(new JLabel("Cycles:"));
		simOpts.add(cycles);
		simOpts.add(new JLabel("Coral:"));
		simOpts.add(corals);
		
		JPanel rulePane = new JPanel(new BorderLayout());
		File rulesFolder = new File("test/resources");
		File[] files = rulesFolder.listFiles();
		String[] ruleFileNames = new String[files.length];
		for(int i = 0; i < files.length; i++)
		{
			ruleFileNames[i] = files[i].getAbsolutePath();
		}
		final JList<String> ruleFiles = new JList<String>(ruleFileNames);
		ruleFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rulePane.add(ruleFiles);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SimulationParameters simParams = new SimulationParameters(
						endX.getText(),
						endY.getText(),
						endZ.getText(),
						robots.getText(),
						ruleFiles.getSelectedValue(),
						cycles.getText(),
						corals.getText());
				if(simParams.validate())
				{
					errors.setText("");
					Location worldEnd = new Location(simParams.getEndX(), simParams.getEndY(), simParams.getEndZ());
					Simulation sim = new Simulation(worldEnd, simParams.getNumRobots(), new RandomLocationRobotFactory(), simParams.getRulesFile(), simParams.getCycles(), simParams.getCorals());
					sim.run();
					WorldImage wi = new WorldImage(sim.getWorld(),
							WorldAttribute.FREQUENCY,
							WorldAttribute.CORAL,
							WorldAttribute.REEF,
							WorldAttribute.TOPDOWN);
					SimulationResultsPanel simResults = new SimulationResultsPanel(wi, SimulationConfigurationPanel.this.containerWindow);
					SimulationConfigurationPanel.this.containerWindow.setResultsPanel(simResults);
					SimulationConfigurationPanel.this.containerWindow.showPanel(ApplicationPanel.RESULTS);
				}
				else
				{
					for(String error : simParams.getErrors())
					{
						errors.append(error);
					}
				}
			}          
		});
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SimulationConfigurationPanel.this.containerWindow.showPanel(ApplicationPanel.START);
			}
		});
		
		buttonPanel.add(backButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(runButton);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		contentPanel.add(simOpts);
		contentPanel.add(rulePane);
		
		errors.setEditable(false);
		contentPanel.add(errors);
		contentPanel.add(buttonPanel);
		
		setLayout(new BorderLayout());
		add(contentPanel);
	}
}
