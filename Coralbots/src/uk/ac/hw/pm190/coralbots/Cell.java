package uk.ac.hw.pm190.coralbots;

/**
 * 
 * @author Patrick Mackinder
 */
public class Cell
{
	private final Location location;
	private CellContent contents;

	public Cell(Location location)
	{
		this.location = location;
		this.contents = new Water();
	}

	public Location getLocation()
	{
		return location;
	}

	public CellContent getContents()
	{
		return contents;
	}

	public void setContents(CellContent content) throws CellNotEmptyException
	{
		if(contents.getCellContentType() == CellContentType.WATER)
		{
			contents = content;
			return;
		}
		throw new CellNotEmptyException();
	}
}
