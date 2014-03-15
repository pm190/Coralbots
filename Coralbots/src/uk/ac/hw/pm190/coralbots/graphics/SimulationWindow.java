package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.RandomLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationWindow extends JFrame implements ItemListener
{
	private static final long serialVersionUID = 1L;
	private final JPanel imagePane = new JPanel(new CardLayout());
	private final JPanel contentPane = new JPanel(new BorderLayout());
	JScrollPane scroll = new JScrollPane(contentPane);
	
	public SimulationWindow()
	{
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
		simOpts.add(new JLabel("Robots:"));
		simOpts.add(robots);
		simOpts.add(new JLabel("Cycles:"));
		simOpts.add(cycles);
		simOpts.add(new JLabel("Coral:"));
		simOpts.add(corals);
		
		JPanel rulePane = new JPanel(new BorderLayout());
		JTextArea ruleFiles = new JTextArea();
		ruleFiles.setEditable(false);
		rulePane.add(ruleFiles);
		
		JPanel runButtonPane = new JPanel(new BorderLayout());
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int x = Integer.parseInt(endX.getText());
				int y = Integer.parseInt(endY.getText());
				int z = Integer.parseInt(endZ.getText());
				Location worldEnd = new Location(x,y,z);
				int bots = Integer.parseInt(robots.getText());
				int cyc = Integer.parseInt(cycles.getText());
				int cor = Integer.parseInt(corals.getText());
				Simulation sim = new Simulation(worldEnd, bots, new RandomLocationRobotFactory(), cyc, cor);
				sim.run();
				WorldImage wi = new WorldImage(sim.getWorld(),
						WorldAttribute.FREQUENCY,
						WorldAttribute.CORAL,
						WorldAttribute.ROCK,
						WorldAttribute.REEF,
						WorldAttribute.TOPDOWN);
				displayResults(wi);
			}          
		});
		runButtonPane.add(runButton);
		
		JPanel simConfig = new JPanel();
		simConfig.setLayout(new BoxLayout(simConfig, BoxLayout.PAGE_AXIS));
		simConfig.add(simOpts);
		simConfig.add(rulePane);
		simConfig.add(runButtonPane);
		
		contentPane.add(simConfig);
		
		add(scroll);
		setTitle("Coralbots Simulation Manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void displayResults(WorldImage worldImage)
	{
		worldImage.visitAttributes();
		JPanel containerPane = new JPanel(new BorderLayout());
		JPanel menuPane = new JPanel();
		
		int numPanels = worldImage.getPanels().size();
		WorldAttribute[] attributes = new WorldAttribute[numPanels];
		List<WorldAttributeVisitor> wads = worldImage.getPanels();
		
		WorldAttributeVisitor wad;
		WorldAttribute attr;
		for(int i = 0; i < numPanels; i++)
		{
			wad = wads.get(i);
			attr = wad.getAttribute();
			attributes[i] = attr;
			imagePane.add(wad, attr.toString());
		}
        
		JComboBox<WorldAttribute> cb = new JComboBox<WorldAttribute>(attributes);
        cb.setEditable(false);
        cb.addItemListener(this);
        menuPane.add(cb);
		
        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.LINE_AXIS));
        JTextField score = new JTextField();
        score.setEditable(false);
        score.setText(String.format("Score: %.2f%%", worldImage.getWorld().getRating()));
        
        topPane.add(score);
        topPane.add(menuPane);
        
        containerPane.add(topPane, BorderLayout.NORTH);
		containerPane.add(imagePane, BorderLayout.CENTER);
		contentPane.removeAll();
		contentPane.add(containerPane);
		this.validate();
	}

	@Override
	public void itemStateChanged(ItemEvent evt)
	{
		CardLayout cl = (CardLayout)(imagePane.getLayout());
        cl.show(imagePane, ((WorldAttribute)evt.getItem()).toString());
	}
}
