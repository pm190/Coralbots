package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Represents 3x3 grid pattern, containing CellContentTypes
 * @author Patrick Mackinder
 */
public class Pattern
{
	private final CellContentType[][] pattern;
	
	/**
	 * Create empty pattern, all water cells
	 */
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
	
	/**
	 * Create pattern based on array of CellContentTypes
	 * @param pattern
	 */
	public Pattern(CellContentType[][] pattern)
	{
		this.pattern = pattern;
	}
	
	/**
	 * Create pattern based on array of Cells
	 * @param pattern
	 */
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
	
	/**
	 * Get cell content type using coordinate pair
	 * @param XYPair p
	 * @return CellContentType at position p
	 */
	public CellContentType getCellContentType(XYPair p) 
	{
		return pattern[p.getX()][p.getY()];
	}
}
