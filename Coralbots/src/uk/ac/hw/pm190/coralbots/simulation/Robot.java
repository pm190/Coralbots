package uk.ac.hw.pm190.coralbots.simulation;

import java.util.Collection;

/**
 * 
 * @author Patrick Mackinder
 */
public class Robot implements CellContent
{
	private Location location;
	private CellContent contents;
	private final Collection<Rule> rules;

	public Robot(Location location, Collection<Rule> rules)
	{
		this.location = location;
		contents = null;
		this.rules = rules;
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
			Cell[][] cells = world.getNeighboursBelow(location);
			if(cells != null)
			{
				Cell cell;
				for(int x = 0; x < 3; x++)
				{
					for(int y = 0; y < 3; y++)
					{
						cell = cells[x][y];
						if(cell != null && !cell.isReef() && cell.getContents().getCellContentType() == CellContentType.CORAL)
						{
							contents = cell.pickupContents();
							return;
						}
					}
				}
			}
		}
		else
		{
			try 
			{
				for(Rule rule : rules)
				{
					RuleMatcher ruleMatcher = new RuleMatcher(world, new Location(location, 0, 0, -1), rule);
					if(ruleMatcher.matches())
					{
						world.updateCell(ruleMatcher.getActionCellLocation(), rule.getChangeType().getInstance());
						world.getCell(ruleMatcher.getActionCellLocation()).setReef(true);
						world.attachSurroundingCoralToReef(ruleMatcher.getActionCellLocation(), false);
						contents = null;
					}
				}
			} 
			catch (CellNotEmptyException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public CellContentType getCellContentType()
	{
		return CellContentType.ROBOT;
	}
	
	public CellContent getContents()
	{
		return contents;
	}
}
