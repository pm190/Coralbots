package uk.ac.hw.pm190.coralbots.graphics;

import java.util.Arrays;

import uk.ac.hw.pm190.coralbots.simulation.CellNotEmptyException;
import uk.ac.hw.pm190.coralbots.simulation.DefiniteLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.Simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationWindowTest
{
	public static void main(String[] args) throws CellNotEmptyException
	{
		Location worldStart = new Location(0,0,0);
		Location worldEnd = new Location(50,50,50);
		Location[] locations = new Location[] { new Location(worldStart.getX(), worldStart.getY(), worldStart.getZ()+1), Location.getMiddle(worldStart, worldEnd), worldEnd};
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1000, 50);
		sim.run();

		WorldImage wi = new WorldImage(sim.getWorld(), 
				WorldAttribute.FREQUENCY, 
				WorldAttribute.CORAL, 
				WorldAttribute.ROCK, 
				WorldAttribute.REEF,
				WorldAttribute.TOPDOWN);
		SimulationWindow simWin = new SimulationWindow();
		simWin.display(wi);
	}
}
