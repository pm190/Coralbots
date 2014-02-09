package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.hw.pm190.coralbots.Cell;
import uk.ac.hw.pm190.coralbots.World;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * 
 * @author Patrick Mackinder
 */
@RunWith(DataProviderRunner.class)
public class WorldTest
{
	private static final Location[] worldEnds = new Location[] { new Location(4, 4, 4), new Location(9, 9, 9), new Location(9, 4, 4), new Location(4, 9, 4), new Location(4, 4, 9) };

	@DataProvider
	public static Object[][] validCoordinates()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		for(Location worldEnd : worldEnds)
		{
			Location middleLocation = Location.getMiddle(new Location(0, 0, 0), worldEnd);
			data.add(new Location[] { worldEnd, new Location(0, 0, 0) });
			data.add(new Location[] { worldEnd, middleLocation });
			data.add(new Location[] { worldEnd, worldEnd });
		}
		return data.toArray(new Object[][] {});
	}

	@DataProvider
	public static Object[][] invalidCoordinates()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		for(Location worldEnd : worldEnds)
		{
			data.add(new Location[] { worldEnd, new Location(-1, 0, 0) });
			data.add(new Location[] { worldEnd, new Location(0, -1, 0) });
			data.add(new Location[] { worldEnd, new Location(0, 0, -1) });
			data.add(new Location[] { worldEnd, new Location(worldEnd.getX() + 1, 0, 0) });
			data.add(new Location[] { worldEnd, new Location(0, worldEnd.getY() + 1, 0) });
			data.add(new Location[] { worldEnd, new Location(0, 0, worldEnd.getZ() + 1) });
		}
		return data.toArray(new Object[][] {});
	}

	@DataProvider
	public static Object[][] neighboursForValidCoordinates()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		for(Location worldEnd : worldEnds)
		{
			Location middleLocation = Location.getMiddle(new Location(0, 0, 0), worldEnd);
			// Cell not on any edge
			data.add(new Object[] { worldEnd, middleLocation, 26 });
			// Corner Cells
			data.add(new Object[] { worldEnd, new Location(0, 0, 0), 7 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), 0, 0), 7 });
			data.add(new Object[] { worldEnd, new Location(0, worldEnd.getY(), 0), 7 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), worldEnd.getY(), 0), 7 });
			data.add(new Object[] { worldEnd, new Location(0, 0, worldEnd.getZ()), 7 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), 0, worldEnd.getZ()), 7 });
			data.add(new Object[] { worldEnd, new Location(0, worldEnd.getY(), worldEnd.getZ()), 7 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), worldEnd.getY(), worldEnd.getZ()), 7 });
			// Cell on 2 edges
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, 0), 11 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), 0), 11 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), 0), 11 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), 0), 11 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, worldEnd.getZ()), 11 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), worldEnd.getZ()), 11 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), worldEnd.getZ()), 11 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), worldEnd.getZ()), 11 });
			// Cell on 1 edge
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), middleLocation.getY(), 0), 17 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, middleLocation.getZ()), 17 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), middleLocation.getZ()), 17 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), middleLocation.getZ()), 17 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), middleLocation.getZ()), 17 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), middleLocation.getY(), worldEnd.getZ()), 17 });
		}
		return data.toArray(new Object[][] {});
	}

	@DataProvider
	public static Object[][] neighboursBelowForValidCoordinates()
	{
		Collection<Object[]> data = new ArrayList<Object[]>(3 * worldEnds.length);
		for(Location worldEnd : worldEnds)
		{
			Location middleLocation = Location.getMiddle(new Location(0, 0, 0), worldEnd);
			// Cell not on any edge
			data.add(new Object[] { worldEnd, middleLocation, 9 });
			// Corner Cells
			data.add(new Object[] { worldEnd, new Location(0, 0, 0), 0 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), 0, 0), 0 });
			data.add(new Object[] { worldEnd, new Location(0, worldEnd.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), worldEnd.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(0, 0, worldEnd.getZ()), 4 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), 0, worldEnd.getZ()), 4 });
			data.add(new Object[] { worldEnd, new Location(0, worldEnd.getY(), worldEnd.getZ()), 4 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), worldEnd.getY(), worldEnd.getZ()), 4 });
			// Cell on 2 edges
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, 0), 0 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, worldEnd.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), worldEnd.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), worldEnd.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), worldEnd.getZ()), 6 });
			// Cell on 1 edge
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), middleLocation.getY(), 0), 0 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), 0, middleLocation.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(0, middleLocation.getY(), middleLocation.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(worldEnd.getX(), middleLocation.getY(), middleLocation.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), worldEnd.getY(), middleLocation.getZ()), 6 });
			data.add(new Object[] { worldEnd, new Location(middleLocation.getX(), middleLocation.getY(), worldEnd.getZ()), 9 });
		}
		return data.toArray(new Object[][] {});
	}

	@DataProvider
	public static Object[][] invalidWorldEndCooridnates()
	{
		Collection<Object> data = new ArrayList<Object>();
		data.add(new Location[] { new Location(0, 0, 0) });
		data.add(new Location[] { new Location(-1, -1, -1) });
		data.add(new Location[] { new Location(2, 0, 2) });
		data.add(new Location[] { new Location(0, 2, 2) });
		data.add(new Location[] { new Location(2, 2, 0) });
		return data.toArray(new Object[][] {});
	}

	@Test
	@UseDataProvider("neighboursForValidCoordinates")
	public void getNeighbours_validCoordinates_succeeds(Location end, Location cellLocation, int expectedNumberOfNeighbours)
	{
		World w = new World(end);
		Collection<Cell> neighbours = w.getNeighbours(cellLocation);
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, neighbours.size());

		Set<Cell> neighboursSet = new HashSet<Cell>(neighbours.size());
		neighboursSet.addAll(neighbours);
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());

		for(Cell neighbour : neighbours)
		{
			assertTrue("XYZ coordinate difference <= 1", neighbour.getLocation().isNeighbour(cellLocation));
		}

		assertFalse("Not neighbour of itself", neighbours.contains(w.getCell(cellLocation)));
	}

	@Test
	@UseDataProvider("neighboursBelowForValidCoordinates")
	public void getNeighboursBelow_validCoordinates_succeeds(Location end, Location cellLocation, int expectedNumberOfNeighbours)
	{
		World w = new World(end);
		Collection<Cell> neighbours = w.getNeighboursBelow(cellLocation);
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, neighbours.size());

		Set<Cell> neighboursSet = new HashSet<Cell>(neighbours.size());
		neighboursSet.addAll(neighbours);
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());

		for(Cell neighbour : neighbours)
		{
			assertTrue("X coordinate difference <= 1", Math.abs(neighbour.getLocation().getX() - cellLocation.getX()) <= 1);
			assertTrue("Y coordinate difference <= 1", Math.abs(neighbour.getLocation().getY() - cellLocation.getY()) <= 1);
			assertEquals("Z coordinate", cellLocation.getZ() - 1, neighbour.getLocation().getZ());
		}
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	@UseDataProvider("invalidCoordinates")
	public void getNeighbours_invalidCoordinates_throwsException(Location end, Location cellLocation)
	{
		World w = new World(end);
		w.getNeighbours(cellLocation);
	}

	@Test
	@UseDataProvider("validCoordinates")
	public void getCell_validCoordinates_succeeds(Location end, Location cellLocation)
	{
		World w = new World(end);
		Cell c = w.getCell(cellLocation);
		assertEquals("Cell coordinates", cellLocation, c.getLocation());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	@UseDataProvider("invalidCoordinates")
	public void getCell_invalidCoordinates_throwsException(Location end, Location cellLocation)
	{
		World w = new World(end);
		w.getCell(cellLocation);
	}

	@Test(expected = IllegalArgumentException.class)
	@UseDataProvider("invalidWorldEndCooridnates")
	public void createWorld_invalidCoordinates_throwsException(Location end)
	{
		World w = new World(end);
	}
}
