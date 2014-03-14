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
		Location newLoc;
		while(attempts < 5)
		{
			try
			{
				//TODO movement rules, for now just move randomly up to 3 cells away
				newLoc = location.randomLocation(world.getEnd(), 3);
				//location = new Location(location.getX()+1, location.getY()+1, location.getZ()+1);
				world.updateCell(newLoc, this);
				world.getCell(location).departRobot();
				world.getCell(newLoc).incrementVisitedCount();
				location = newLoc;
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
