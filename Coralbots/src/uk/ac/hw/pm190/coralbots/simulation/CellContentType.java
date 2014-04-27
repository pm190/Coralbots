package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public enum CellContentType
{
	WATER(new Water(), "Water"),
	CORAL(new Coral(), "Coral"),
	ROCK(new Rock(), "Rock"),
	ROBOT(null, "Robot");
	
	private final CellContent cellContent;
	private final String name;
	
	CellContentType(CellContent cellContent, String name)
	{
		this.cellContent = cellContent;
		this.name = name;
	}
	
	CellContent getInstance()
	{
		return cellContent;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
