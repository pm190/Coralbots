package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Patrick Mackinder
 */
public class RandomLocationRobotFactory implements RobotFactory
{
	@Override
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd)
	{
		int xLimit = worldEnd.getX();
		int yLimit = worldEnd.getY();
		int zLimit = worldEnd.getZ();

		List<Robot> robots = new ArrayList<Robot>();
		List<Location> locations = new ArrayList<Location>();
		Location loc;
		Random random = new Random();
		for(int i = 0; i < numberOfRobots; i++)
		{
			loc = new Location(random.nextInt(xLimit+1), random.nextInt(yLimit+1), random.nextInt(zLimit+1));
			while(locations.contains(loc))
			{
				loc = new Location(random.nextInt(xLimit+1), random.nextInt(yLimit+1), random.nextInt(zLimit+1));
			}
			locations.add(loc);
			robots.add(new Robot(loc));
		}
		return robots;
	}
}
