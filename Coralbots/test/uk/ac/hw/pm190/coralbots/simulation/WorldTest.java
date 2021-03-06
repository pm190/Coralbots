package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

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
 * Test cases for World class
 * @author Patrick Mackinder
 */
@RunWith(DataProviderRunner.class)
public class WorldTest
{
	private static final Location[] worldEnds = new Location[] { new Location(4, 4, 4), new Location(9, 9, 9), new Location(9, 4, 4), new Location(4, 9, 4), new Location(4, 4, 9) };

	/**
	 * Generate array of valid coordinates
	 * @return
	 */
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

	/**
	 * Generate array of invalid coordinates
	 * @return
	 */
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

	/**
	 * Generate array of neighbour below coordinates
	 * @return
	 */
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

	/**
	 * Generate array of invlaid world end coordinates
	 * @return
	 */
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

	/**
	 * Test for valid neighbours below
	 * @param end
	 * @param cellLocation
	 * @param expectedNumberOfNeighbours
	 */
	@Test
	@UseDataProvider("neighboursBelowForValidCoordinates")
	public void getNeighboursBelow_validCoordinates_succeeds(Location end, Location cellLocation, int expectedNumberOfNeighbours)
	{
		World w = new World(end);
		Cell[][] neighbours = w.getNeighboursBelow(cellLocation);
		Set<Cell> neighboursSet = new HashSet<Cell>(9);
		int totalNeighbours = 0;
		if(neighbours != null)
		{
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
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, totalNeighbours);
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());
	}

	/**
	 * Test if worlds are created that contain valid coordinate
	 * @param end
	 * @param cellLocation
	 */
	@Test
	@UseDataProvider("validCoordinates")
	public void getCell_validCoordinates_succeeds(Location end, Location cellLocation)
	{
		World w = new World(end);
		Cell c = w.getCell(cellLocation);
		assertEquals("Cell coordinates", cellLocation, c.getLocation());
	}

	/**
	 * Test to see location is out of world bounds
	 * @param end
	 * @param cellLocation
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	@UseDataProvider("invalidCoordinates")
	public void getCell_invalidCoordinates_throwsException(Location end, Location cellLocation)
	{
		World w = new World(end);
		w.getCell(cellLocation);
	}

	/**
	 * Test to see if world can be created with invalid coordinates
	 * @param end
	 */
	@Test(expected = IllegalArgumentException.class)
	@UseDataProvider("invalidWorldEndCooridnates")
	public void createWorld_invalidCoordinates_throwsException(Location end)
	{
		@SuppressWarnings("unused")
		World w = new World(end);
	}

	/**
	 * Test to see if robots are correctly inserted
	 * @throws CellNotEmptyException
	 * @throws IllegalArgumentException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	@Test
	public void insertRobots_validCoordinates_succeeds() throws CellNotEmptyException, IllegalArgumentException, SAXException, IOException, ParserConfigurationException
	{
		Location worldEnd = new Location(4, 4, 4);
		World w = new World(worldEnd);
		Location[] locations = new Location[] { new Location(1, 1, 1), new Location(2, 2, 2), new Location(1, 3, 4) };
		List<Robot> robots = new DefiniteLocationRobotFactory(Arrays.asList(locations)).createRobots(locations.length, worldEnd, new File("test/resources/validRules.xml"));
		w.insertRobots(robots);
		for(Robot robot : robots)
		{
			assertEquals("Inserted Robot", w.getCell(robot.getLocation()).getContents(), robot);
		}
	}
	
	/**
	 * Test to see if correct number of coral is inserted
	 */
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
	
	/**
	 * Test to see if initial coral piece is correctly places
	 */
	@Test
	public void middleBottomCellAlwaysCoral_succeeds()
	{
		Location end = new Location(9,9,9);
		World w = new World(end);
		Location middle = Location.getMiddle(new Location(0,0,0), end);
		CellContentType cc = w.getCell(new Location(middle.getX(), middle.getY(), 1)).getContents().getCellContentType();
		assertEquals("Middle bottom cell above rock layer is coral", CellContentType.CORAL, cc);
	}
	
	/**
	 * Test to see if initial coral reef is set correctly
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws CellNotEmptyException
	 */
	@Test
	public void initialReef_succeeds() throws ArrayIndexOutOfBoundsException, CellNotEmptyException
	{
		Location end = new Location(9,9,9);
		World w = new World(end);
		Location middle = Location.getMiddle(new Location(0,0,0), end);
		Location initialCoral = new Location(middle.getX(), middle.getY(), 1);
		for(int i = 1; i < 4; i++)
		{
			w.updateCell(new Location(initialCoral.getX()+i, initialCoral.getY()+i, initialCoral.getZ()+i), new Coral());
		}
		w.attachSurroundingCoralToReef(initialCoral, true);
		int totalCoralInReef = 0;
		for(int x = 0; x <= end.getX(); x++)
		{
			for(int y = 0; y <= end.getY(); y++)
			{
				for(int z = 0; z <= end.getZ(); z++)
				{
					if(w.getCell(new Location(x,y,z)).isReef())
					{
						totalCoralInReef++;
					}
				}
			}
		}
		assertEquals("Total Reef Cells", 4, totalCoralInReef);
	}
}
