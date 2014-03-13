package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.Color;
import uk.ac.hw.pm190.coralbots.simulation.Cell;

/**
 * 
 * @author Patrick Mackinder
 */
public class FrequencyVisitor extends WorldAttributeDisplay
{
	private static final long serialVersionUID = 1L;

	public FrequencyVisitor(int sizex, int sizey, WorldAttribute attribute)
	{
		super(sizex, sizey, attribute);
	}

	@Override
	public void visit(int x, int y, Cell[] column)
	{
		int colVis = columnVisited(column);
		if(colVis != 0)
		{
			setColor(x, y, new Color(Math.min(255, colVis*10), 0, 0));
		}
		else
		{
			setColor(x, y, new Color(0, 0, 255));
		}
	}
	
	private int columnVisited(Cell[] column)
	{
		int total = 0;
		for(int z = 0; z < column.length; z++)
		{
			total += column[z].getVisitedCount();
		}
		return total;
	}
}
