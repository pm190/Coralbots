package uk.ac.hw.pm190.coralbots;

import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class Simulation
{
	public Simulation(Location end, int numberOfRobots, RobotFactory robotFactory)
	{
		World world = new World(end);
		List<Robot> robots = robotFactory.createRobots(numberOfRobots, end);
		for(Robot robot : robots)
		{
			
		}
	}
}
