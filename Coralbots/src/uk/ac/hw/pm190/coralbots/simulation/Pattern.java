package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Pattern
{
	private final CellContent[][] pattern;
	
	public Pattern(CellContent[][] pattern)
	{
		this.pattern = pattern;
	}
	
	public Pattern(Cell[][] pattern)
	{
		CellContent[][] pat = new CellContent[3][3];
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				pat[y][x] = pattern[y][x].getContents();
			}
		}
		this.pattern = pat;
	}
	
	public CellContent[][] getPattern()
	{
		return pattern;
	}
	
	public boolean equals(Pattern p)
	{
		CellContent[][] pat = p.getPattern();
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x< 3; x++)
			{
				if(pattern[y][x].getCellContentType() != pat[y][x].getCellContentType())
				{
					return false;
				}
			}
		}
		return true;
	}
}
