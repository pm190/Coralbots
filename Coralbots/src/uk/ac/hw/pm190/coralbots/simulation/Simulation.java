package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Represents one simulation.
 * @author Patrick Mackinder
 */
public class Simulation
{
	private final World world;
	private final int cycles;
	
	/**
	 * Creates simulation given specified parameters, but does not run until run method is called.
	 * @param end
	 * @param numberOfRobots
	 * @param robotFactory
	 * @param rules
	 * @param cycles
	 * @param corals
	 */
	public Simulation(Location end, int numberOfRobots, RobotFactory robotFactory, File rules, int cycles, int corals)
	{
		world = new World(end);
		this.cycles = cycles;
		List<Robot> robots;
		try
		{
			robots = robotFactory.createRobots(numberOfRobots, end, rules);
			world.insertCoral(corals);
			world.insertRobots(robots);
		}
		catch(SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Runs the simulation
	 */
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
