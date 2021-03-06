package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Resposible for matching a rule to a given location in the world
 * @author Patrick Mackinder
 */
public class RuleMatcher
{
	private final World world;
	private final Location location;
	private final Rule rule;
	private final Rotation rotation;
	private final boolean ruleMatched;

	public RuleMatcher(World w, Location l, Rule r)
	{
		this.world = w;
		this.location = l;
		this.rule = r;
		Rotation matchedRotation = Rotation.NORTH;
		boolean matched = false;
		for(Rotation rotation : Rotation.values())
		{
			PatternMatcher upperMatcher = new PatternMatcher(world, location, r.getUpperPattern(), rotation);
			PatternMatcher lowerMatcher = new PatternMatcher(world, new Location(location, 0, 0, -1), r.getLowerPattern(), rotation);
			if(upperMatcher.matches() && lowerMatcher.matches() && (upperMatcher.isPartOfReef() || lowerMatcher.isPartOfReef()))
			{
				matchedRotation = rotation;
				matched = true;
				break;
			}
		}
		this.rotation = matchedRotation;
		this.ruleMatched = matched;
	}

	public boolean matches()
	{
		return ruleMatched;
	}

	public Location getActionCellLocation()
	{
		XYPair actionXYPair = rotation.transform(rule.getActionCellXYPair());
		return new Location(location, actionXYPair.getX(), actionXYPair.getY(), 0);
	}
}
