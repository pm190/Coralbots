package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Rule
{
	private final Pattern pattern;
	private final PatternCellLocation cellToChange;
	private final CellContentType changeType;
	
	public Rule(Pattern pattern, PatternCellLocation cellToChange, CellContentType changeType)
	{
		this.pattern = pattern;
		this.cellToChange = cellToChange;
		this.changeType = changeType;
	}

	public Pattern getPattern()
	{
		return pattern;
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
		if(pattern == null)
		{
			if(other.pattern != null)
				return false;
		}
		else if(!pattern.equals(other.pattern))
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
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		return result;
	}
}
