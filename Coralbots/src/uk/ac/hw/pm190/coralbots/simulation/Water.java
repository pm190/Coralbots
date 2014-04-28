package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Water cell content type
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
