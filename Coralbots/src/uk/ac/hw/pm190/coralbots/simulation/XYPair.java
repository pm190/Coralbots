package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Represents a 2D coordinate of (x,y)
 * @author Patrick Mackinder
 */
public class XYPair
{
	private final int x;
	private final int y;

	public XYPair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}
