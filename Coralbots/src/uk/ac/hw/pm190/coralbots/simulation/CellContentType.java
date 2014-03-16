package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public enum CellContentType
{
	WATER(new Water()),
	CORAL(new Coral()),
	ROCK(new Rock()),
	ROBOT(null);
	
	private final CellContent cellContent;
	CellContentType(CellContent cellContent)
	{
		this.cellContent = cellContent;
	}
	
	CellContent getCellContent()
	{
		return cellContent;
	}
}
