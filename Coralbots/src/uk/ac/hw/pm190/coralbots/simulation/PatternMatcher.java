package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Pattern matcher is responsible for matching a pattern to given location in the world
 * @author Patrick Mackinder
 */
public class PatternMatcher
{
	private final boolean patternMatched;
	private final boolean partOfReef;

	/**
	 * Attempts to match pattern to world location, if so sets patternMatched true and partOfReef true
	 * if pattern matched is part of central coral reef
	 * @param world
	 * @param location
	 * @param pattern
	 * @param rotation
	 */
	public PatternMatcher(World world, Location location, Pattern pattern, Rotation rotation) 
	{
		boolean matched = true;
		boolean reef = false;
		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				Location loc = new Location(location, x, y, 0);
				try
				{
					if(world.getCell(loc).getContents().getCellContentType() != pattern.getCellContentType(new XYPair(x+1, y+1)))
					{
						matched = false;
					}
					if(world.getCell(loc).isReef())
					{
						reef = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					matched = false;
				}
			}
		}
		patternMatched = matched;
		partOfReef = reef;
	}

	public boolean matches() 
	{
		return patternMatched;
	}

	/**
	 * @return the partOfReef
	 */
	public boolean isPartOfReef() 
	{
		return partOfReef;
	}
}
