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
		if(hasReef(column))
		{
			setColor(x, y, new Color(0, 255, 0));
		}
		else
		{
			setColor(x, y, new Color(0, 0, 255));
		}
	}
	
	private boolean hasReef(Cell[] column)
	{
		for(int z = 0; z < column.length; z++)
		{
			if(column[z].isReef())
			{
				return true;
			}
		}
		return false;
	}
}
