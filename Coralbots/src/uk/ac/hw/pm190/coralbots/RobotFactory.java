package uk.ac.hw.pm190.coralbots;

import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public interface RobotFactory
{
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd);
}
