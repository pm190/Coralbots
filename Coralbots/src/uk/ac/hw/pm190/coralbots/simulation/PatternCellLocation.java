package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public enum PatternCellLocation
{
	TOPLEFT(-1,-1, "topLeft"),
	TOP(0,-1, "top"),
	TOPRIGHT(1,-1, "topRight"),
	MIDDLELEFT(-1,0, "middleLeft"),
	MIDDLE(0,0, "middle"),
	MIDDLERIGHT(1,0, "middleRight"),
	BOTTOMLEFT(-1,1, "bottomLeft"),
	BOTTOM(0,1, "bottom"),
	BOTTOMRIGHT(1,1, "bottomRight");
	
	private final int x;
	private final int y;
	private final String name;
	
	PatternCellLocation(int x, int y, String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
