package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

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
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1);
		sim.run();
		assertFalse("Robot location changed", sim.getWorld().getRobots().get(0).getLocation().equals(robotLocation));
	}
	
	@Test(expected = CellNotEmptyException.class)
	public void insertRobots_sameLocation_fails() throws CellNotEmptyException
	{
		Location worldEnd = new Location(9,9,9);
		Location[] locations = new Location[] { new Location(1,1,1), new Location(1,1,1) };
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		@SuppressWarnings("unused")
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1);
	}
}
