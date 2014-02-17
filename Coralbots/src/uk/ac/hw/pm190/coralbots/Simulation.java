package uk.ac.hw.pm190.coralbots;

import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class Simulation
{
	private final World world;
	
	public Simulation(Location end, int numberOfRobots, RobotFactory robotFactory, int cycles)
	{
		world = new World(end);
		List<Robot> robots = robotFactory.createRobots(numberOfRobots, end);
		world.insertRobots(robots);
		
		for(int i = 0; i < cycles; i++)
		{
			world.updateRobots();
		}
	}

	public World getWorld()
	{
		return world;
	}
}
