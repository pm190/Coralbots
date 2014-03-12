package uk.ac.hw.pm190.coralbots.graphics;

import uk.ac.hw.pm190.coralbots.simulation.Cell;

/**
 * 
 * @author Patrick Mackinder
 */
public interface ColumnVisitor
{
	public void visit(int x, int y, Cell[] column);
}
