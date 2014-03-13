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
	public static void main(String[] args) throws CellNotEmptyException
	{
		Location worldStart = new Location(0,0,0);
		Location worldEnd = new Location(50,50,50);
		Location[] locations = new Location[] { worldStart, Location.getMiddle(worldStart, worldEnd), worldEnd};
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1000, 50);
		sim.run();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WorldImage wi = new WorldImage(sim.getWorld(), WorldAttribute.FREQUENCY, WorldAttribute.CORAL);
		wi.visitAttributes();
		frame.add(wi.getPanels().get(0));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
