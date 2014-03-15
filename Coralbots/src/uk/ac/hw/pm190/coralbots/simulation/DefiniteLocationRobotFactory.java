package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd, File rules) throws IllegalArgumentException, SAXException, IOException, ParserConfigurationException
	{
		if(numberOfRobots != locations.size())
		{
			throw new IllegalArgumentException();
		}
		else
		{
			List<Robot> robots = new ArrayList<Robot>(numberOfRobots);
			Collection<Rule> ruleList = Rules.parseRulesFile(new File("resources/rules.xsd"), rules);
			for(int i = 0; i < numberOfRobots; i++)
			{
				robots.add(new Robot(locations.get(i), ruleList));
			}
			return robots;
		}
	}
}
