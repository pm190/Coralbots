package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Rock cell type
 * @author Patrick Mackinder
 */
public class Rock implements CellContent
{
	@Override
	public CellContentType getCellContentType()
	{
		return CellContentType.ROCK;
	}
}
