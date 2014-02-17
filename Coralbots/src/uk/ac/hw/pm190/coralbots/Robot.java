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
	
	public void update(World world)
	{
		//MOVE
		world.updateCell(location, new Water());
		//TODO movement rules, for now just move randomly up to 3 cells away
		location = location.randomLocation(world.getEnd(), 3);
		world.updateCell(location, this);
		System.out.println(String.format("Robot location (%d, %d, %d)", location.getX(), location.getY(), location.getZ()));
		
		//ACT
		
	}
}
