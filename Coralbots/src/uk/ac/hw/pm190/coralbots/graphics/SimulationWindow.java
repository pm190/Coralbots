package uk.ac.hw.pm190.coralbots.graphics;

import javax.swing.JFrame;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public SimulationWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
