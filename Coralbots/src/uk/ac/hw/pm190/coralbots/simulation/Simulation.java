package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * 
 * @author Patrick Mackinder
 */
public class Simulation
{
	private final World world;
	private final int cycles;
	
	public Simulation(Location end, int numberOfRobots, RobotFactory robotFactory, File rules, int cycles, int corals)
	{
		world = new World(end);
		this.cycles = cycles;
		List<Robot> robots;
		try
		{
			robots = robotFactory.createRobots(numberOfRobots, end, rules);
			world.insertCoral(corals);
			float result = world.getRating();
			System.out.print(String.format("%.2f\t", result));
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
