package uk.ac.hw.pm190;

public class Cell 
{
	private final int x;
	private final int y;
	private final int z;
	
	public Cell(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX()
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getZ() 
	{
		return z;
	}
}
