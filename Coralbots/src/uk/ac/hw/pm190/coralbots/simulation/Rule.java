package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Rule
{
	private final Pattern upperPattern;
	private final Pattern lowerPattern;
	private final PatternCellLocation cellToChange;
	private final CellContentType changeType;
	
	public Rule(Pattern upperPattern, Pattern lowerPattern, PatternCellLocation cellToChange, CellContentType changeType)
	{
		//TODO validate rules, valid/could be valid (runtime)/invalid
		this.upperPattern = upperPattern;
		this.lowerPattern = lowerPattern;
		this.cellToChange = cellToChange;
		this.changeType = changeType;
	}

	public Pattern getUpperPattern()
	{
		return upperPattern;
	}

	public Pattern getLowerPattern()
	{
		return lowerPattern;
	}

	public PatternCellLocation getCellToChange()
	{
		return cellToChange;
	}

	public CellContentType getChangeType()
	{
		return changeType;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if(cellToChange != other.cellToChange)
			return false;
		if(changeType != other.changeType)
			return false;
		if(lowerPattern == null)
		{
			if(other.lowerPattern != null)
				return false;
		}
		else if(!lowerPattern.equals(other.lowerPattern))
			return false;
		if(upperPattern == null)
		{
			if(other.upperPattern != null)
				return false;
		}
		else if(!upperPattern.equals(other.upperPattern))
			return false;
		return true;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellToChange == null) ? 0 : cellToChange.hashCode());
		result = prime * result + ((changeType == null) ? 0 : changeType.hashCode());
		result = prime * result + ((lowerPattern == null) ? 0 : lowerPattern.hashCode());
		result = prime * result + ((upperPattern == null) ? 0 : upperPattern.hashCode());
		return result;
	}
}
