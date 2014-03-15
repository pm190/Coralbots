package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import uk.ac.hw.pm190.coralbots.simulation.CellNotEmptyException;
import uk.ac.hw.pm190.coralbots.simulation.DefiniteLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.Simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationTest
{
	@Test
	public void robotMoved_succeeds() throws CellNotEmptyException
	{
		Location worldEnd = new Location(9,9,9);
		Location robotLocation = new Location(1,1,1);
		Location[] locations = new Location[] { robotLocation };
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, new File("test/resources/validRules.xml"), 1, 0);
		sim.run();
		assertFalse("Robot location changed", sim.getWorld().getRobots().get(0).getLocation().equals(robotLocation));
	}
}
