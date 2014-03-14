package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.Color;

import uk.ac.hw.pm190.coralbots.simulation.Cell;
import uk.ac.hw.pm190.coralbots.simulation.CellContentType;

/**
 * 
 * @author Patrick Mackinder
 */
public class TopDownVisitor extends WorldAttributeVisitor
{
	private static final long serialVersionUID = 1L;

	public TopDownVisitor(int sizex, int sizey)
	{
		super(sizex, sizey, WorldAttribute.TOPDOWN);
	}

	@Override
	public void visit(int x, int y, Cell[] column)
	{
		CellContentType cellType;
		for(int z = column.length-1; z >= 0; z--)
		{
			cellType = column[z].getContents().getCellContentType();
			if(cellType != CellContentType.WATER)
			{
				switch(cellType)
				{
					case CORAL:
					{
						setColor(x, y, new Color(0,255,0));
						break;
					}
					case ROCK:
					{
						setColor(x, y, new Color(160, 160, 160));
						break;
					}
					case ROBOT:
					{
						setColor(x, y, new Color(255, 0, 0));
						break;
					}
					default:
						break;
				}
				return;
			}
		}
		setColor(x, y, new Color(0,0,255));
	}
}
