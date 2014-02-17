package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author Patrick Mackinder
 */
public class SimulationTest
{
	@Test
	public void robotMoved()
	{
		Location worldEnd = new Location(9,9,9);
		Location robotLocation = new Location(1,1,1);
		Location[] locations = new Location[] { robotLocation };
		DefiniteLocationRobotFactory robotFactory = new DefiniteLocationRobotFactory(Arrays.asList(locations));
		Simulation sim = new Simulation(worldEnd, locations.length, robotFactory, 1);
		assertFalse("Robot location changed", sim.getWorld().getRobots().get(0).getLocation().equals(robotLocation));
	}
}
