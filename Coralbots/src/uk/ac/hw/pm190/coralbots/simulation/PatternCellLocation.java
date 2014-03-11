package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public enum PatternCellLocation
{
	TOPLEFT(0,0),
	TOP(1,0),
	TOPRIGHT(2,0),
	MIDDLELEFT(0,1),
	MIDDLE(1,1),
	MIDDLERIGHT(2,1),
	BOTTOMLEFT(0,2),
	BOTTOM(1,2),
	BOTTOMRIGHT(2,2);
	
	private final int x;
	private final int y;
	
	PatternCellLocation(int x, int y)
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
