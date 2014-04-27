package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Pattern
{
	private final CellContentType[][] pattern;
	
	public Pattern()
	{
		pattern = new CellContentType[3][3];
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				pattern[x][y] = CellContentType.WATER;
			}
		}
	}
	
	public Pattern(CellContentType[][] pattern)
	{
		this.pattern = pattern;
	}
	
	public Pattern(Cell[][] pattern)
	{
		CellContentType[][] pat = new CellContentType[3][3];
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				pat[x][y] = pattern[x][y].getContents().getCellContentType();
			}
		}
		this.pattern = pat;
	}
	
	public CellContentType getCellContentType(XYPair p) 
	{
		return pattern[p.getX()][p.getY()];
	}
}
