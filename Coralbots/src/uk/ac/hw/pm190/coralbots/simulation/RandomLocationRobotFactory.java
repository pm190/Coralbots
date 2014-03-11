package uk.ac.hw.pm190.coralbots.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class RandomLocationRobotFactory implements RobotFactory
{
	@Override
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd)
	{
		List<Robot> robots = new ArrayList<Robot>();
		List<Location> locations = new ArrayList<Location>();
		Location loc;
		//TODO infinite loop if too many robots
		for(int i = 0; i < numberOfRobots; i++)
		{
			loc = Location.randomLocation(worldEnd);
			while(locations.contains(loc))
			{
				loc = Location.randomLocation(worldEnd);
			}
			locations.add(loc);
			robots.add(new Robot(loc));
		}
		return robots;
	}
}
