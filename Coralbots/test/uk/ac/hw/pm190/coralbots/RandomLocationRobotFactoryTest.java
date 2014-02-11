package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * 
 * @author Patrick Mackinder
 */
@RunWith(DataProviderRunner.class)
public class RandomLocationRobotFactoryTest
{
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
	
	@Test
	@UseDataProvider("validRobotNumbersAndWorldEnds")
	public void createRobots_validNumber_suceeds(Location worldEnd, int robotNumber)
	{
		RandomLocationRobotFactory rFactory = new RandomLocationRobotFactory();
		List<Robot> robots = rFactory.createRobots(robotNumber, worldEnd);
		assertEquals(robots.size(), robotNumber);
	}

	@Test
	@UseDataProvider("validRobotNumbersAndWorldEnds")
	public void createRubots_validNumber_differentLocations_succeeds(Location worldEnd, int robotNumber)
	{
		RandomLocationRobotFactory rFactory = new RandomLocationRobotFactory();
		List<Robot> robots = rFactory.createRobots(robotNumber, worldEnd);
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
