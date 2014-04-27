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
	
	public Rule()
	{
		upperPattern = new Pattern();
		lowerPattern = new Pattern();
		cellToChange = PatternCellLocation.TOPLEFT;
		changeType = CellContentType.WATER;
	}

	public Pattern getUpperPattern()
	{
		return upperPattern;
	}

	public Pattern getLowerPattern()
	{
		return lowerPattern;
	}

	public CellContentType getChangeType()
	{
		return changeType;
	}
	
	public PatternCellLocation getCellToChange()
	{
		return cellToChange;
	}

	public XYPair getActionCellXYPair() 
	{
		return new XYPair(cellToChange.getX(), cellToChange.getY()); 
	}
}
