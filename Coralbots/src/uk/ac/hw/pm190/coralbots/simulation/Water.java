package uk.ac.hw.pm190.coralbots.simulation;

/**
 * 
 * @author Patrick Mackinder
 */
public class Water implements CellContent
{
	@Override
	public CellContentType getCellContentType()
	{
		return CellContentType.WATER;
	}
}
