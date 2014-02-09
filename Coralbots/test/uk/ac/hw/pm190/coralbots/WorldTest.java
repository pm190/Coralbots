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

@RunWith(DataProviderRunner.class)
public class WorldTest 
{
	
	private static final int[] worldLengths = new int[]{5, 10};
	
	@DataProvider
	public static Object[][] validCoordinates() 
	{
		Collection<Object[]> data = new ArrayList<Object[]>(3 * worldLengths.length);
		for (int lengthX : worldLengths) 
		{
			int minXIndex = 0;
			int midXIndex = lengthX / 2;
			int maxXIndex = lengthX - 1;
			for(int lengthY : worldLengths) 
			{
				int minYIndex = 0;
				int midYIndex = lengthY / 2;
				int maxYIndex = lengthY - 1;
				for(int lengthZ : worldLengths) 
				{
					int minZIndex = 0;
					int midZIndex = lengthZ / 2;
					int maxZIndex = lengthZ - 1;
					data.add(new Integer[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, minZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, midZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, maxZIndex });
				}
			}
		}
		return data.toArray(new Object[][]{});
	}
	
	@DataProvider
	public static Object[][] invalidCoordinates() 
	{
		Collection<Object[]> data = new ArrayList<Object[]>(3 * worldLengths.length);
		for (int lengthX : worldLengths) 
		{
			int minXIndex = 0;
			int maxXIndex = lengthX - 1;
			for(int lengthY : worldLengths) 
			{
				int minYIndex = 0;
				int maxYIndex = lengthY - 1;
				for(int lengthZ : worldLengths) 
				{
					int minZIndex = 0;
					int maxZIndex = lengthZ - 1;
					data.add(new Integer[] { lengthX, lengthY, lengthZ, minXIndex-1, minYIndex, minZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex-1, minZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, minZIndex-1 });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, maxXIndex+1, maxYIndex, maxZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex+1, maxZIndex });
					data.add(new Integer[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, maxZIndex+1 });
				}
			}
		}
		return data.toArray(new Object[][]{});
	}
	
	@DataProvider
	public static Object[][] neighboursForValidCoordinates() {
		Collection<Object[]> data = new ArrayList<Object[]>(3 * worldLengths.length);
		for (int lengthX : worldLengths) 
		{
			int minXIndex = 0;
			int midXIndex = lengthX / 2;
			int maxXIndex = lengthX - 1;
			for(int lengthY : worldLengths) 
			{
				int minYIndex = 0;
				int midYIndex = lengthY / 2;
				int maxYIndex = lengthY - 1;
				for(int lengthZ : worldLengths) 
				{
					int minZIndex = 0;
					int midZIndex = lengthZ / 2;
					int maxZIndex = lengthZ - 1;
					// Cell not on any edge
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, midZIndex, 26});
					// Corner Cells
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, minZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, minYIndex, minZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, maxYIndex, minZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, minZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, maxZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, minYIndex, maxZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, maxYIndex, maxZIndex, 7});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, maxZIndex, 7});
					// Cell on 2 edges
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, minZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, minZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, minZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, minZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, maxZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, maxZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, maxZIndex, 11});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, maxZIndex, 11});
					// Cell on 1 edge
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, minZIndex, 17});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, midZIndex, 17});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, midZIndex, 17});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, midZIndex, 17});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, midZIndex, 17});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, maxZIndex, 17});
				}
			}
		}
		return data.toArray(new Object[][]{});
	}
	
	@DataProvider
	public static Object[][] neighboursBelowForValidCoordinates() 
	{
		Collection<Object[]> data = new ArrayList<Object[]>(3 * worldLengths.length);
		for (int lengthX : worldLengths) 
		{
			int minXIndex = 0;
			int midXIndex = lengthX / 2;
			int maxXIndex = lengthX - 1;
			for(int lengthY : worldLengths) 
			{
				int minYIndex = 0;
				int midYIndex = lengthY / 2;
				int maxYIndex = lengthY - 1;
				for(int lengthZ : worldLengths) 
				{
					int minZIndex = 0;
					int midZIndex = lengthZ / 2;
					int maxZIndex = lengthZ - 1;
					// Cell not on any edge
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, midZIndex, 9});
					// Corner Cells
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, minYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, maxYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, minYIndex, maxZIndex, 4});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, minYIndex, maxZIndex, 4});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, maxYIndex, maxZIndex, 4});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, maxYIndex, maxZIndex, 4});
					// Cell on 2 edges
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, maxZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, maxZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, maxZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, maxZIndex, 6});
					// Cell on 1 edge
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, minZIndex, 0});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, minYIndex, midZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, minXIndex, midYIndex, midZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, maxXIndex, midYIndex, midZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, maxYIndex, midZIndex, 6});
					data.add(new Object[] { lengthX, lengthY, lengthZ, midXIndex, midYIndex, maxZIndex, 9});
				}
			}
		}
		return data.toArray(new Object[][]{});
	}

	@Test
	@UseDataProvider("neighboursForValidCoordinates")
	public void getNeighbours_validCoordinates_succeeds(int lengthX, int lengthY, int lengthZ, int x, int y, int z, int expectedNumberOfNeighbours) 
	{
		World w = new World(lengthX, lengthY, lengthZ);
		Collection<Cell> neighbours = w.getNeighbours(x,y,z);
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, neighbours.size());
		
		Set<Cell> neighboursSet = new HashSet<Cell>(neighbours.size());
		neighboursSet.addAll(neighbours);
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());
		
		for(Cell neighbour : neighbours)
		{
			assertTrue("X coordinate difference <= 1", Math.abs(neighbour.getX()-x) <= 1);
			assertTrue("Y coordinate difference <= 1", Math.abs(neighbour.getY()-y) <= 1);
			assertTrue("Z coordinate difference <= 1", Math.abs(neighbour.getZ()-z) <= 1);
		}
		
		assertFalse("Not neighbour of itself", neighbours.contains(w.getCell(x, y, z)));
	}
	
	@Test
	@UseDataProvider("neighboursBelowForValidCoordinates")
	public void getNeighboursBelow_validCoordinates_succeeds(int lengthX, int lengthY, int lengthZ, int x, int y, int z, int expectedNumberOfNeighbours) 
	{
		World w = new World(lengthX, lengthY, lengthZ);
		Collection<Cell> neighbours = w.getNeighboursBelow(x,y,z);
		assertEquals("Number of neighbours", expectedNumberOfNeighbours, neighbours.size());
		
		Set<Cell> neighboursSet = new HashSet<Cell>(neighbours.size());
		neighboursSet.addAll(neighbours);
		assertEquals("Number of unique neighbours", expectedNumberOfNeighbours, neighboursSet.size());
		
		for(Cell neighbour : neighbours)
		{
			assertTrue("X coordinate difference <= 1", Math.abs(neighbour.getX()-x) <= 1);
			assertTrue("Y coordinate difference <= 1", Math.abs(neighbour.getY()-y) <= 1);
			assertEquals("Z coordinate", z-1, neighbour.getZ());
		}
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	@UseDataProvider("invalidCoordinates")
	public void getNeighbours_invalidCoordinates_throwsException(int lengthX, int lengthY, int lengthZ, int x, int y, int z) 
	{
		World w = new World(lengthX, lengthY, lengthZ);
		w.getNeighbours(x, y, z);
	}
	
	@Test
	@UseDataProvider("validCoordinates")
	public void getCell_validCoordinates_succeeds(int lengthX, int lengthY, int lengthZ, int x, int y, int z) 
	{
		World w = new World(lengthX, lengthY, lengthZ);
		Cell c = w.getCell(x, y, z);
		assertEquals("X coordinate", x, c.getX());
		assertEquals("Y coordinate", y, c.getY());
		assertEquals("Z coordinate", z, c.getZ());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	@UseDataProvider("invalidCoordinates")
	public void getCell_invalidCoordinates_throwsException(int lengthX, int lengthY, int lengthZ, int x, int y, int z) 
	{
		World w = new World(lengthX, lengthY, lengthZ);
		w.getCell(x, y, z);
	}
}
