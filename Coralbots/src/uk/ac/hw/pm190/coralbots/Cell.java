package uk.ac.hw.pm190.coralbots;

/**
 * 
 * @author Patrick Mackinder
 */
public class Cell
{
	private final Location location;
	private CellContent contents;
	private int visitedCount;

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
	
	public void departRobot()
	{
		if(contents.getCellContentType() == CellContentType.ROBOT)
		{
			contents = new Water();
		}
	}

	public int getVisitedCount()
	{
		return visitedCount;
	}
	
	public void incrementVisitedCount()
	{
		visitedCount++;
	}
}
