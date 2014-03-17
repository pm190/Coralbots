package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Cell
{
	private final Location location;
	private CellContent contents;
	private int visitedCount;
	private boolean isReef;

	public Cell(Location location)
	{
		this.location = location;
		this.contents = CellContentType.WATER.getInstance();
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
	
	public CellContent pickupContents()
	{
		CellContent temp = contents;
		contents = new Water();
		return temp;
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

	public boolean isReef()
	{
		return isReef;
	}

	public void setReef(boolean isReef)
	{
		this.isReef = isReef;
	}
}
