package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.Color;

import uk.ac.hw.pm190.coralbots.simulation.Cell;

/**
 * 
 * @author Patrick Mackinder
 */
public class ReefVisitor extends WorldAttributeVisitor
{
	private static final long serialVersionUID = 1L;

	public ReefVisitor(int sizex, int sizey)
	{
		super(sizex, sizey, WorldAttribute.REEF);
	}

	@Override
	public void visit(int x, int y, Cell[] column)
	{
		CoralStatus cs = hasReef(column);
		if(cs.isReef() && cs.isInitialReef())
		{
			setColor(x, y, new Color(0, 255, 255));
		}
		else if(cs.isReef())
		{
			setColor(x, y, new Color(0, 255, 0));
		}
		else
		{
			setColor(x, y, new Color(0, 0, 255));
		}
	}
	
	private CoralStatus hasReef(Cell[] column)
	{
		for(int z = column.length-1; z >= 0; z--)
		{
			if(column[z].isReef())
			{
				if(column[z].isInitialReef())
				{
					return new CoralStatus(true, true);
				}
				return new CoralStatus(true, false);
			}
		}
		return new CoralStatus(false, false);
	}
}

final class CoralStatus
{
	private final boolean isReef;
	private final boolean isInitialReef;
	
	public CoralStatus(boolean isReef, boolean isInitialReef)
	{
		this.isReef = isReef;
		this.isInitialReef = isInitialReef;
	}

	/**
	 * @return the isReef
	 */
	public boolean isReef() 
	{
		return isReef;
	}

	/**
	 * @return the isInitialReef
	 */
	public boolean isInitialReef() 
	{
		return isInitialReef;
	}
}
