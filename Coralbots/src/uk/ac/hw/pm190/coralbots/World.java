package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
import java.util.Collection;

public class World 
{
	private final int lengthX, lengthY, lengthZ;
	private final Cell[][][] cells;
	
	public World(int lengthX, int lengthY, int lengthZ)
	{
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.lengthZ = lengthZ;
		cells = new Cell[lengthX][lengthY][lengthZ];
		
		for(int z = 0; z < lengthZ; z++)
		{
			for(int y = 0; y < lengthY; y++)
			{
				for(int x = 0; x < lengthX; x++)
				{
					cells[x][y][z] = new Cell(x,y,z);
				}
			}
		}
	}

	public Cell getCell(int x, int y, int z) throws ArrayIndexOutOfBoundsException 
	{	
		return cells[x][y][z];
	}
	
	public Collection<Cell> getNeighbours(int cellX, int cellY, int cellZ) throws ArrayIndexOutOfBoundsException
	{
		Collection<Cell> neighbours = new ArrayList<Cell>();
		Cell c = this.getCell(cellX, cellY, cellZ);
		for(int z = Math.max(0, cellZ - 1); z <= Math.min(lengthZ-1, cellZ + 1); z++)
		{
			for(int y = Math.max(0, cellY - 1); y <= Math.min(lengthY-1, cellY + 1); y++)
			{
				for(int x = Math.max(0, cellX - 1); x <= Math.min(lengthX-1, cellX + 1); x++)
				{
					Cell neighbour = this.getCell(x, y, z);
					if(neighbour != c)
					{
						neighbours.add(neighbour);
					}
				}
			}
		}
		return neighbours;
	}

	public Collection<Cell> getNeighboursBelow(int cellX, int cellY, int cellZ) throws ArrayIndexOutOfBoundsException
	{
		if(cellZ < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		Collection<Cell> neighbours = new ArrayList<Cell>();
		if(cellZ == 0)
		{
			return neighbours;
		}
		int z = cellZ - 1;
		for(int y = Math.max(0, cellY - 1); y <= Math.min(lengthY-1, cellY + 1); y++)
		{
			for(int x = Math.max(0, cellX - 1); x <= Math.min(lengthX-1, cellX + 1); x++)
			{
				neighbours.add(this.getCell(x, y, z));
			}
		}
		return neighbours;
	}
}
