package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
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
	private final List<Robot> robots = new ArrayList<Robot>();

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
	
	public void updateCell(Location location, CellContent content) throws ArrayIndexOutOfBoundsException, CellNotEmptyException
	{
		Cell cell = getCell(location);
		cell.setContents(content);
	}
	
	public void insertRobots(List<Robot> bots) throws CellNotEmptyException
	{
		for(Robot robot : bots)
		{
			updateCell(robot.getLocation(), robot);
			robots.add(robot);
		}
	}
	
	public void updateRobots()
	{
		//Will do nothing if not used insertRobots
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
		Cell[][] neighbours = new Cell[3][3];
		if(location.getZ() == 0)
		{
			return neighbours;
		}
		int z = location.getZ() - 1;
		int i = 0, j = 0;
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
}
