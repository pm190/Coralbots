package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.RandomLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Robot;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * RandomLocationRobotFactory test cases
 * @author Patrick Mackinder
 */
@RunWith(DataProviderRunner.class)
public class RandomLocationRobotFactoryTest
{
	/**
	 * Generate array of worldEnds and numbers of robots to create
	 * @return
	 */
	@DataProvider
	public static Object[][] validRobotNumbersAndWorldEnds()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { new Location(4,4,4), 3 });
		data.add(new Object[] { new Location(4,4,4), 5 });
		data.add(new Object[] { new Location(9,9,9), 8 });
		data.add(new Object[] { new Location(9,9,9), 10 });
		return data.toArray(new Object[][] {});
	}
	
	/**
	 * Test to see if correct numbers are created for different sized worlds
	 * @param worldEnd
	 * @param robotNumber
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	@Test
	@UseDataProvider("validRobotNumbersAndWorldEnds")
	public void createRobots_validNumber_suceeds(Location worldEnd, int robotNumber) throws SAXException, IOException, ParserConfigurationException
	{
		RandomLocationRobotFactory rFactory = new RandomLocationRobotFactory();
		List<Robot> robots = rFactory.createRobots(robotNumber, worldEnd, new File("test/resources/validRules.xml"));
		assertEquals(robots.size(), robotNumber);
	}

	/**
	 * Test to see if robots are generated in different locations from each other
	 * @param worldEnd
	 * @param robotNumber
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	@Test
	@UseDataProvider("validRobotNumbersAndWorldEnds")
	public void createRubots_validNumber_differentLocations_succeeds(Location worldEnd, int robotNumber) throws SAXException, IOException, ParserConfigurationException
	{
		RandomLocationRobotFactory rFactory = new RandomLocationRobotFactory();
		List<Robot> robots = rFactory.createRobots(robotNumber, worldEnd, new File("test/resources/validRules.xml"));
		for(int i = 0; i < robots.size(); i++)
		{
			for(int j = 0; j < robots.size(); j++)
			{
				if(i == j)
				{
					continue;
				}
				else
				{
					assertNotEquals(robots.get(i).getLocation(), robots.get(j).getLocation());
				}
			}
		}
	}
}
