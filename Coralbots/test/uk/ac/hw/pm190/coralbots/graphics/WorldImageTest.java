package uk.ac.hw.pm190.coralbots.graphics;

import java.util.Arrays;

import javax.swing.JFrame;

import uk.ac.hw.pm190.coralbots.graphics.WorldImage;
import uk.ac.hw.pm190.coralbots.simulation.CellNotEmptyException;
import uk.ac.hw.pm190.coralbots.simulation.DefiniteLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.Simulation;

public class WorldImageTest
{
	private static final int WINDOW_LEFT_BORDER = 26;
	private static final int WINDOW_TOP_BORDER = 48;
	
	public static void main(String[] args) throws CellNotEmptyException
	{
		Location worldStart = new Location(0,0,0);
		Location worldEnd = new Location(50,50,50);
		Location[] locations = new Location[] { worldStart, Location.getMiddle(worldStart, worldEnd), worldEnd};
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1000);
		sim.run();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new WorldImage(sim.getWorld()));
        frame.setSize(worldEnd.getX()*10 + WINDOW_LEFT_BORDER, worldEnd.getY()*10 + WINDOW_TOP_BORDER);
        frame.setVisible(true);
	}
}
