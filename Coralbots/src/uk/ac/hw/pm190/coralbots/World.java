package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Patrick Mackinder
 */
public class World
{
	private final Location end;
	private final int xLength, yLength, zLength;
	private final Cell[][][] cells;
	private List<Robot> robots;

	public World(Location end) throws IllegalArgumentException
	{
		if(end.getX() < 2 || end.getY() < 2 || end.getZ() < 2)
		{
			throw new IllegalArgumentException();
		}
		this.end = end;
		this.xLength = end.getX()+1;
		this.yLength = end.getY()+1;
		this.zLength = end.getZ()+1;
		cells = new Cell[xLength][yLength][zLength];

		for(int z = 0; z < zLength; z++)
		{
			for(int y = 0; y < yLength; y++)
			{
				for(int x = 0; x < xLength; x++)
				{
					cells[x][y][z] = new Cell(new Location(x,y,z));
				}
			}
		}
	}

	public Cell getCell(Location location) throws ArrayIndexOutOfBoundsException
	{
		return cells[location.getX()][location.getY()][location.getZ()];
	}
	
	public void updateCell(Location location, CellContent content) throws ArrayIndexOutOfBoundsException
	{
		Cell cell = getCell(location);
		cell.setContents(content);
	}
	
	public void insertRobots(List<Robot> bots)
	{
		for(Robot robot : bots)
		{
			updateCell(robot.getLocation(), robot);
			robots.add(robot);
		}
	}
	
	public void updateRobots()
	{
		for(Robot robot : robots)
		{
			updateCell(robot.getLocation(), new Water());
			updateCell(robot.getNextLocation(end), robot);
		}
	}

	public Collection<Cell> getNeighbours(Location location) throws ArrayIndexOutOfBoundsException
	{
		Collection<Cell> neighbours = new ArrayList<Cell>();
		Cell c = this.getCell(location);
		for(int z = Math.max(0, location.getZ() - 1); z <= Math.min(zLength - 1, location.getZ() + 1); z++)
		{
			for(int y = Math.max(0, location.getY() - 1); y <= Math.min(yLength - 1, location.getY() + 1); y++)
			{
				for(int x = Math.max(0, location.getX() - 1); x <= Math.min(xLength - 1, location.getX() + 1); x++)
				{
					Cell neighbour = this.getCell(new Location(x, y, z));
					if(neighbour != c)
					{
						neighbours.add(neighbour);
					}
				}
			}
		}
		return neighbours;
	}

	public Collection<Cell> getNeighboursBelow(Location location) throws ArrayIndexOutOfBoundsException
	{
		if(location.getZ() < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		Collection<Cell> neighbours = new ArrayList<Cell>();
		if(location.getZ() == 0)
		{
			return neighbours;
		}
		int z = location.getZ() - 1;
		for(int y = Math.max(0, location.getY() - 1); y <= Math.min(yLength - 1, location.getY() + 1); y++)
		{
			for(int x = Math.max(0, location.getX() - 1); x <= Math.min(xLength - 1, location.getX() + 1); x++)
			{
				neighbours.add(this.getCell(new Location(x, y, z)));
			}
		}
		return neighbours;
	}
}
