package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.Color;

import uk.ac.hw.pm190.coralbots.simulation.Cell;
import uk.ac.hw.pm190.coralbots.simulation.CellContentType;

/**
 * 
 * @author Patrick Mackinder
 */
public class RockVisitor extends WorldAttributeVisitor
{
	private static final long serialVersionUID = 1L;

	public RockVisitor(int sizex, int sizey)
	{
		super(sizex, sizey, WorldAttribute.ROCK);
	}

	@Override
	public void visit(int x, int y, Cell[] column)
	{
		if(hasRock(column))
		{
			setColor(x, y, new Color(255, 165, 0));
		}
		else
		{
			setColor(x, y, new Color(0, 0, 255));
		}
	}
	
	private boolean hasRock(Cell[] column)
	{
		for(int z = 0; z < column.length; z++)
		{
			if(column[z].getContents().getCellContentType() == CellContentType.ROCK)
			{
				return true;
			}
		}
		return false;
	}
}
