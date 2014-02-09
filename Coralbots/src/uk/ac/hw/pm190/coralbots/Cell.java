package uk.ac.hw.pm190.coralbots;

public class Cell 
{
	private final int x,y,z;
	
	public Cell(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
}
