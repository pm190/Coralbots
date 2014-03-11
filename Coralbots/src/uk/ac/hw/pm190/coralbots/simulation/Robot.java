package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Robot implements CellContent
{
	private Location location;
	private CellContent contents;

	public Robot(Location location)
	{
		this.location = location;
		contents = null;
	}

	public Location getLocation()
	{
		return location;
	}
	
	public void update(World world) throws ArrayIndexOutOfBoundsException
	{
		//MOVE
		int attempts = 0;
		while(attempts < 5)
		{
			try
			{
				Location old = location;
				//TODO movement rules, for now just move randomly up to 3 cells away
				location = location.randomLocation(world.getEnd(), 3);
				//location = new Location(location.getX()+1, location.getY()+1, location.getZ()+1);
				world.updateCell(location, this);
				world.getCell(old).departRobot();
				world.getCell(location).incrementVisitedCount();
				break;
			}
			catch(CellNotEmptyException | ArrayIndexOutOfBoundsException e)
			{
				attempts++;
			}
		}
		
		//ACT
		if(contents == null)
		{
			//TODO match pattern/pickup content
		}
		else
		{
			
		}
	}

	@Override
	public CellContentType getCellContentType()
	{
		return CellContentType.ROBOT;
	}
}
