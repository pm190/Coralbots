package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class PatternMatcher
{
	private final boolean patternMatched;
	
	public PatternMatcher(World world, Location location, Pattern pattern, Rotation rotation) 
	{
		boolean matched = true;
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
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					matched = false;
				}
			}
		}
		patternMatched = matched;
	}

	public boolean matches() {
		return patternMatched;
	}
}
