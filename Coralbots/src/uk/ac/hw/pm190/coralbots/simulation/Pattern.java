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
		//Facing same way
		boolean match = patternMatches(pat);
		if(match)
		{
			return true;
		}
		
		//Rotated 90 degrees
		CellContentType[][] rotatedPattern = rotate90Degrees(pat);
		match = patternMatches(rotatedPattern);
		if(match)
		{
			return true;
		}
		
		//Rotated 180 degrees
		rotatedPattern = rotate180Degrees(pat);
		match = patternMatches(rotatedPattern);
		if(match)
		{
			return true;
		}
		
		//Rotated 270 degrees
		rotatedPattern = rotate270Degrees(pat);
		return patternMatches(rotatedPattern);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pattern);
		return result;
	}
	
	private boolean patternMatches(CellContentType[][] pattern)
	{
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				if(this.pattern[x][y] != pattern[x][y])
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private CellContentType[][] rotate90Degrees(CellContentType[][] pattern)
	{
		CellContentType[][] rotated = new CellContentType[3][3];
		int xPat = 0, yPat = 0;
		for(int y = 0; y < 3; y++)
		{
			yPat = 0;
			for(int x = 2; x >= 0; x--)
			{
				rotated[xPat][yPat] = pattern[x][y];
				yPat++;
			}
			xPat++;
		}
		return rotated;
	}
	
	private CellContentType[][] rotate180Degrees(CellContentType[][] pattern)
	{
		CellContentType[][] rotated = new CellContentType[3][3];
		int xPat = 0, yPat = 0;
		for(int x = 2; x >= 0; x--)
		{
			yPat = 0;
			for(int y = 2; y >= 0; y--)
			{
				rotated[xPat][yPat] = pattern[x][y];
				yPat++;
			}
			xPat++;
		}
		return rotated;
	}
	
	private CellContentType[][] rotate270Degrees(CellContentType[][] pattern)
	{
		CellContentType[][] rotated = new CellContentType[3][3];
		int xPat = 0, yPat = 0;
		for(int y = 2; y >= 0; y--)
		{
			yPat = 0;
			for(int x = 0; x < 3; x++)
			{
				rotated[xPat][yPat] = pattern[x][y];
				yPat++;
			}
			xPat++;
		}
		return rotated;
	}
}
