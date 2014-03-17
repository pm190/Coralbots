package uk.ac.hw.pm190.coralbots.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Patrick Mackinder
 */
public class World
{
	private final Location end;
	private final int xLength, yLength, zLength;
	private final Cell[][][] cells;
	private final List<Robot> robots = new ArrayList<Robot>();

	public World(Location end) throws IllegalArgumentException
	{
		if(end.getX() < 2 || end.getY() < 2 || end.getZ() < 2)
		{
			throw new IllegalArgumentException();
		}
		this.end = end;
		this.xLength = end.getX() + 1;
		this.yLength = end.getY() + 1;
		this.zLength = end.getZ() + 1;
		cells = new Cell[xLength][yLength][zLength];

		for(int z = 0; z < zLength; z++)
		{
			for(int y = 0; y < yLength; y++)
			{
				for(int x = 0; x < xLength; x++)
				{
					cells[x][y][z] = new Cell(new Location(x, y, z));
					if(z == 0)
					{
						try
						{
							cells[x][y][z].setContents(CellContentType.ROCK.getInstance());
						}
						catch(CellNotEmptyException e)
						{
							// Should never get here
						}
					}
				}
			}
		}
		
		try
		{
			Location middle = Location.getMiddle(new Location(0,0,0), end);
			Location initialReefCell = new Location(middle.getX(), middle.getY(), 1);
			updateCell(initialReefCell, new Coral());
			setReefCell(initialReefCell, true);
		}
		catch(ArrayIndexOutOfBoundsException | CellNotEmptyException e)
		{
			// TODO try to place coral somewhere nearby the middle
		}
	}

	public Cell getCell(Location location) throws ArrayIndexOutOfBoundsException
	{
		return cells[location.getX()][location.getY()][location.getZ()];
	}

	public void updateCell(Location location, CellContent content) throws ArrayIndexOutOfBoundsException, CellNotEmptyException
	{
		Cell cell = getCell(location);
		cell.setContents(content);
	}

	public void insertRobots(List<Robot> bots)
	{
		for(Robot robot : bots)
		{
			try
			{
				updateCell(robot.getLocation(), robot);
			}
			catch(CellNotEmptyException e)
			{
				//TODO find new place for robot
			}
			getCell(robot.getLocation()).incrementVisitedCount();
			robots.add(robot);
		}
	}

	public void updateRobots()
	{
		// Will do nothing if not used insertRobots
		for(Robot robot : robots)
		{
			robot.update(this);
		}
	}

	public Cell[][] getNeighboursBelow(Location location) throws ArrayIndexOutOfBoundsException
	{
		if(location.getZ() < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if(location.getZ() == 0)
		{
			return null;
		}
		int z = location.getZ() - 1;
		int i = 0, j = 0;
		Cell[][] neighbours = new Cell[3][3];
		for(int y = Math.max(0, location.getY() - 1); y <= Math.min(yLength - 1, location.getY() + 1); y++)
		{
			i = 0;
			for(int x = Math.max(0, location.getX() - 1); x <= Math.min(xLength - 1, location.getX() + 1); x++)
			{
				neighbours[j][i] = this.getCell(new Location(x, y, z));
				i++;
			}
			j++;
		}
		return neighbours;
	}

	public Location getEnd()
	{
		return end;
	}

	public List<Robot> getRobots()
	{
		return robots;
	}

	/**
	 * Insert number of coral blocks into simulation. Note that the number of coral
	 * will be 1 greater than value specified in numCorals due to world inserting
	 * a coral block at world centre during construction
	 * 
	 * @param numCorals
	 * @throws IllegalArgumentException
	 */
	public void insertCoral(int numCorals) throws IllegalArgumentException
	{
		int endX = end.getX() + 1;
		int endY = end.getY() + 1;
		int endZ = end.getZ() + 1;

		if(numCorals > ((endX * endY * endZ) / 4))
		{
			// Too much coral
			throw new IllegalArgumentException();
		}

		if(numCorals > 0)
		{
			Random rand = new Random();
			int rx = 0, ry = 0, rz = 0, i = 1;
			while(i < numCorals)
			{
				rx = rand.nextInt(endX);
				ry = rand.nextInt(endY);
				rz = 0;
				while(rz < endZ)
				{
					try
					{
						updateCell(new Location(rx, ry, rz), new Coral());
						break;
					}
					catch(CellNotEmptyException e)
					{
						rz++;
					}
				}
				if(rz != endZ)
				{
					i++;
				}
			}
		}
		attachSurroundingCoralToReef();
	}
	
	public void attachSurroundingCoralToReef()
	{
		//TODO get initReefLocation, possible field in world class
		Location middle = Location.getMiddle(new Location(0,0,0), end);
		Location initialReefLocation = new Location(middle.getX(), middle.getY(), 1);
		Set<Cell> reef = new HashSet<Cell>();
		setReef(reef, getNeighbours(initialReefLocation));
		for(Cell cell : reef)
		{
			setReefCell(cell.getLocation(), true);
		}
	}
	
	private void setReef(Set<Cell> reef, Collection<Cell> cells)
	{
		for(Cell cell : cells)
		{
			if(cell.getContents().getCellContentType() == CellContentType.CORAL)
			{
				if(reef.add(cell))
				{
					setReef(reef, getNeighbours(cell.getLocation()));
				}
			}
		}
	}
	
	private Collection<Cell> getNeighbours(Location loc) throws ArrayIndexOutOfBoundsException
	{
		int middleX = loc.getX();
		int middleY = loc.getY();
		int middleZ = loc.getZ();
		Cell cell = getCell(loc);
		Cell neighbour;
		Collection<Cell> neighbours = new ArrayList<Cell>();
		for(int x = middleX - 1; x <= middleX + 1; x++)
		{
			for(int y = middleY - 1; y <= middleY + 1; y++)
			{
				for(int z = middleZ - 1; z <= middleZ + 1; z++)
				{
					neighbour = getCell(new Location(x, y, z));
					if(cell != neighbour)
					{
						try
						{
							neighbours.add(getCell(new Location(x,y,z)));
						}
						catch(ArrayIndexOutOfBoundsException e)
						{
							//Dont add cell, outside of world
						}
					}
				}
			}
		}
		return neighbours;
	}
	
	public Cell[] getColumn(int x, int y) throws ArrayIndexOutOfBoundsException
	{	
		int endZ = end.getZ()+1;
		Cell[] column = new Cell[endZ];
		for(int z = 0; z < endZ; z++)
		{
			column[z] = getCell(new Location(x,y,z));
		}
		return column;
	}
	
	public void setReefCell(Location location, boolean isReef)
	{
		getCell(location).setReef(isReef);
	}

	public float getRating()
	{
		int numReef = 0;
		int numCoral = 0;
		Cell cell;
		for(int z = 0; z < zLength; z++)
		{
			for(int y = 0; y < yLength; y++)
			{
				for(int x = 0; x < xLength; x++)
				{
					cell = cells[x][y][z];
					if(cell.getContents().getCellContentType() == CellContentType.CORAL)
					{
						numCoral++;
						if(cell.isReef())
						{
							numReef++;
						}
					}
				}
			}
		}
		return ((float)numReef/(float)numCoral)*100;
	}
}
