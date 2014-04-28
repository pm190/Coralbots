package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Creates robots at random locations
 * @author Patrick Mackinder
 */
public class RandomLocationRobotFactory implements RobotFactory
{
	@Override
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd, File rules) throws SAXException, IOException, ParserConfigurationException
	{
		List<Robot> robots = new ArrayList<Robot>();
		List<Location> locations = new ArrayList<Location>();
		Location loc;
		Collection<Rule> ruleList = Rules.parseRulesFile(new File("resources/rules.xsd"), rules);
		//TODO infinite loop if too many robots
		for(int i = 0; i < numberOfRobots; i++)
		{
			loc = Location.randomLocation(worldEnd);
			while(locations.contains(loc))
			{
				loc = Location.randomLocation(worldEnd);
			}
			locations.add(loc);
			robots.add(new Robot(loc, ruleList));
		}
		return robots;
	}
}
