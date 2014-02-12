package uk.ac.hw.pm190.coralbots;

/**
 * 
 * @author Patrick Mackinder
 */
public class Robot implements CellContent
{
	private Location location;

	public Robot(Location location)
	{
		this.location = location;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public Location getNextLocation(Location end)
	{
		//TODO movement rules, for now just move randomly up to 3 cells away
		return location.randomLocation(end, 3);
	}
}
