package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class DefiniteLocationRobotFactory implements RobotFactory
{
	private final List<Location> locations;
	
	public DefiniteLocationRobotFactory(List<Location> locations)
	{
		this.locations = locations;
	}
	
	@Override
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd) throws IllegalArgumentException
	{
		if(numberOfRobots != locations.size())
		{
			throw new IllegalArgumentException();
		}
		else
		{
			List<Robot> robots = new ArrayList<Robot>(numberOfRobots);
			for(int i = 0; i < numberOfRobots; i++)
			{
				robots.add(new Robot(locations.get(i)));
			}
			return robots;
		}
	}
}
