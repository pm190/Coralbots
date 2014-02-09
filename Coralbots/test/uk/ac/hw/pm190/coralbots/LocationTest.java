package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

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
public class LocationTest
{
	@DataProvider
	public static Object[][] validNeighbours()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		Location middleLocation = new Location(1, 1, 1);
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				for(int z = 0; z < 3; z++)
				{
					if(x != 1 && y != 1 && z != 1)
					{
						data.add(new Location[] { middleLocation, new Location(x, y, z) });
					}
				}
			}
		}
		return data.toArray(new Object[][] {});
	}
	
	@DataProvider
	public static Object[][] invalidNeighbours()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		Location middleLocation = new Location(2, 2, 2);
		//Bottom
		data.add(new Location[] { middleLocation, new Location(0, 0, 0) });
		data.add(new Location[] { middleLocation, new Location(4, 0, 0) });
		data.add(new Location[] { middleLocation, new Location(0, 4, 0) });
		data.add(new Location[] { middleLocation, new Location(4, 4, 0) });
		data.add(new Location[] { middleLocation, new Location(2, 2, 0) });
		//Top
		data.add(new Location[] { middleLocation, new Location(0, 0, 4) });
		data.add(new Location[] { middleLocation, new Location(4, 0, 4) });
		data.add(new Location[] { middleLocation, new Location(0, 4, 4) });
		data.add(new Location[] { middleLocation, new Location(4, 4, 4) });
		data.add(new Location[] { middleLocation, new Location(2, 2, 4) });
		//Side Middles
		data.add(new Location[] { middleLocation, new Location(2, 0, 2) });
		data.add(new Location[] { middleLocation, new Location(0, 2, 2) });
		data.add(new Location[] { middleLocation, new Location(4, 2, 2) });
		data.add(new Location[] { middleLocation, new Location(2, 4, 2) });
		return data.toArray(new Object[][] {});
	}
	
	@DataProvider
	public static Object[][] locations()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Location[] { new Location(0,0,0), new Location(4, 0, 0), new Location(2,0,0) });
		data.add(new Location[] { new Location(0,0,0), new Location(0, 4, 0), new Location(0,2,0) });
		data.add(new Location[] { new Location(0,0,0), new Location(0, 0, 4), new Location(0,0,2) });
		data.add(new Location[] { new Location(6,0,0), new Location(0, 0, 0), new Location(3,0,0) });
		data.add(new Location[] { new Location(0,6,0), new Location(0, 0, 0), new Location(0,3,0) });
		data.add(new Location[] { new Location(0,0,6), new Location(0, 0, 0), new Location(0,0,3) });
		data.add(new Location[] { new Location(0,0,0), new Location(0, 8, 8), new Location(0,4,4) });
		data.add(new Location[] { new Location(0,0,0), new Location(8, 0, 8), new Location(4,0,4) });
		data.add(new Location[] { new Location(0,0,0), new Location(8, 8, 0), new Location(4,4,0) });
		data.add(new Location[] { new Location(0,0,0), new Location(10, 10, 10), new Location(5,5,5) });
		data.add(new Location[] { new Location(0,0,0), new Location(5, 5, 5), new Location(2,2,2) });
		data.add(new Location[] { new Location(0,0,0), new Location(7, 0, 7), new Location(3,0,3) });
		data.add(new Location[] { new Location(0,0,0), new Location(0, 3, 0), new Location(0,1,0) });
		return data.toArray(new Object[][] {});
	}

	@Test
	@UseDataProvider("validNeighbours")
	public void isNeighbour_validNeighbours_succeeds(Location middle, Location neighbour)
	{
		assertTrue("Is neighbour", middle.isNeighbour(neighbour));
	}

	@Test
	@UseDataProvider("invalidNeighbours")
	public void isNeighbour_invalidNeighbours_fails(Location middle, Location neighbour)
	{
		assertFalse("Is not neighbour", middle.isNeighbour(neighbour));
	}

	@Test
	@UseDataProvider("locations")
	public void getMiddle_succeeds(Location start, Location end, Location expectedMiddle)
	{
		Location middle = Location.getMiddle(start, end);
		assertEquals("Middle location", middle, expectedMiddle);
	}
}
