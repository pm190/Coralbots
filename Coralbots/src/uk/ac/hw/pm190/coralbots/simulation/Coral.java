package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Represent Coral piece
 * @author Patrick Mackinder
 */
public class Coral implements CellContent
{
	@Override
	public CellContentType getCellContentType()
	{
		return CellContentType.CORAL;
	}
}
