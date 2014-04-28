package uk.ac.hw.pm190.coralbots.simulation;

/**
 * The cell class represents a cube in the world. It retains the amount of times it has been visited
 * and also if it is part of the reef and initial reef.
 * @author Patrick Mackinder
 */
public class Cell
{
	private final Location location;
	private CellContent contents;
	private int visitedCount;
	private boolean isReef;
	private boolean initialReef;

	/**
	 * Create a cell at the specified location
	 * @param location
	 */
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

	/**
	 * Sets cell content only if it already contains Water, otherwise it throws CellNotEmptyException
	 * @param content
	 * @throws CellNotEmptyException
	 */
	public void setContents(CellContent content) throws CellNotEmptyException
	{
		if(contents.getCellContentType() == CellContentType.WATER)
		{
			contents = content;
			return;
		}
		throw new CellNotEmptyException();
	}
	
	/**
	 * Return cell contents and replace contents with Water
	 * @return CellContent
	 */
	public CellContent pickupContents()
	{
		CellContent temp = contents;
		contents = CellContentType.WATER.getInstance();
		return temp;
	}
	
	/**
	 * Remove robot from cell, called when robot moves to new cell
	 */
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

	public boolean isInitialReef() 
	{
		return initialReef;
	}

	public void setInitialReef(boolean initialReef) 
	{
		this.initialReef = initialReef;
	}
}
