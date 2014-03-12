package uk.ac.hw.pm190.coralbots.simulation;

import java.util.Arrays;

/**
 * 
 * @author Patrick Mackinder
 */
public class Pattern
{
	private final CellContentType[][] pattern;
	
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
	
	public CellContentType[][] getPattern()
	{
		return pattern;
	}
	
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Pattern p = (Pattern) obj;
		CellContentType[][] pat = p.getPattern();
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x< 3; x++)
			{
				if(pattern[y][x] != pat[y][x])
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pattern);
		return result;
	}
}
