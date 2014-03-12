package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.hw.pm190.coralbots.simulation.Simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationWindow
{
	private final List<Simulation> simulations = new ArrayList<Simulation>();
	
	public SimulationWindow()
	{
	}
	
	public void draw()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel(new BorderLayout());
		frame.setContentPane(contentPanel);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
