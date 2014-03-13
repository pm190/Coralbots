package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.hw.pm190.coralbots.simulation.Cell;
import uk.ac.hw.pm190.coralbots.simulation.CellNotEmptyException;
import uk.ac.hw.pm190.coralbots.simulation.DefiniteLocationRobotFactory;
import uk.ac.hw.pm190.coralbots.simulation.Location;
import uk.ac.hw.pm190.coralbots.simulation.Robot;
import uk.ac.hw.pm190.coralbots.simulation.World;

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
	@UseDataProvider("neighboursBelowForValidCoordinates")
	public void getNeighboursBelow_validCoordinates_succeeds(Location end, Location cellLocation, int expectedNumberOfNeighbours)
	{
		World w = new World(end);
		Cell[][] neighbours = w.getNeighboursBelow(cellLocation);
		int totalNeighbours = 0;
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				if(neighbours[y][x] != null)
				{
					totalNeighbours++;
				}
			}
		}
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, totalNeighbours);

		Set<Cell> neighboursSet = new HashSet<Cell>(9);
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				if(neighbours[y][x] != null)
				{
					neighboursSet.add(neighbours[y][x]);
				}
			}
		}
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());

		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				if(neighbours[y][x] != null)
				{
					assertTrue("X coordinate difference <= 1", Math.abs(neighbours[y][x].getLocation().getX() - cellLocation.getX()) <= 1);
					assertTrue("Y coordinate difference <= 1", Math.abs(neighbours[y][x].getLocation().getY() - cellLocation.getY()) <= 1);
					assertEquals("Z coordinate", cellLocation.getZ() - 1, neighbours[y][x].getLocation().getZ());
				}
			}
		}
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
		@SuppressWarnings("unused")
		World w = new World(end);
	}

	@Test
	public void insertRobots_validCoordinates_succeeds() throws CellNotEmptyException
	{
		Location worldEnd = new Location(4, 4, 4);
		World w = new World(worldEnd);
		Location[] locations = new Location[] { new Location(1, 1, 1), new Location(2, 2, 2), new Location(1, 3, 4) };
		List<Robot> robots = new DefiniteLocationRobotFactory(Arrays.asList(locations)).createRobots(locations.length, worldEnd);
		w.insertRobots(robots);
		for(Robot robot : robots)
		{
			assertEquals("Inserted Robot", w.getCell(robot.getLocation()).getContents(), robot);
		}
	}
	
	@Test
	public void insertCorals_succeeds()
	{
		int numCoral = 10;
		Location worldEnd = new Location(9, 9, 9);
		World w = new World(worldEnd);
		w.insertCoral(numCoral);
		int endX = w.getEnd().getX();
		int endY = w.getEnd().getY();
		int endZ = w.getEnd().getZ();
		
		int total = 0;
		for(int x = 0; x <= endX; x++)
		{
			for(int y = 0; y <= endY; y++)
			{
				for(int z = 0; z <= endZ; z++)
				{
					if(w.getCell(new Location(x,y,z)).getContents().getCellContentType() == CellContentType.CORAL)
					{
						total++;
					}
				}
			}
		}
		
		assertEquals("Number of coral", numCoral, total);
	}
	
	@Test
	public void middleBottomCellAlwaysCoral_suceeds()
	{
		Location end = new Location(9,9,9);
		World w = new World(end);
		Location middle = Location.getMiddle(new Location(0,0,0), end);
		CellContentType cc = w.getCell(new Location(middle.getX(), middle.getY(), 1)).getContents().getCellContentType();
		assertEquals("Middle bottom cell above rock layer is coral", cc, CellContentType.CORAL);
	}
}
