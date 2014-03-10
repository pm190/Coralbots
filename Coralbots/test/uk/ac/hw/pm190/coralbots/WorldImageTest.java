package uk.ac.hw.pm190.coralbots;

import java.util.Arrays;

import javax.swing.JFrame;

import uk.ac.hw.pm190.graphics.WorldImage;

public class WorldImageTest
{
	public static void main(String[] args) throws CellNotEmptyException
	{
		Location worldEnd = new Location(70,50,50);
		Location[] locations = new Location[] { new Location(0,0,0), new Location(50,50,50) };
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1000);
		sim.run();
		
		JFrame frame = new JFrame();
        frame.add(new WorldImage(sim.getWorld()));
        frame.setSize(worldEnd.getX()*10, worldEnd.getY()*10);
        frame.setVisible(true);
	}
}
