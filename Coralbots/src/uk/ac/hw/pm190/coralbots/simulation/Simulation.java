package uk.ac.hw.pm190.coralbots.simulation;

import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class Simulation
{
	private final World world;
	private final int cycles;
	
	public Simulation(Location end, int numberOfRobots, RobotFactory robotFactory, int cycles) throws CellNotEmptyException
	{
		world = new World(end);
		this.cycles = cycles;
		List<Robot> robots = robotFactory.createRobots(numberOfRobots, end);
		world.insertRobots(robots);
	}
	
	public void run()
	{
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
