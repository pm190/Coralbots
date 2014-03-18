package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public enum PatternCellLocation
{
	TOPLEFT(-1,-1),
	TOP(0,-1),
	TOPRIGHT(1,-1),
	MIDDLELEFT(-1,0),
	MIDDLE(0,0),
	MIDDLERIGHT(1,0),
	BOTTOMLEFT(-1,1),
	BOTTOM(0,1),
	BOTTOMRIGHT(1,1);
	
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
