package uk.ac.hw.pm190.coralbots.simulation;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.hw.pm190.coralbots.simulation.Cell;
import uk.ac.hw.pm190.coralbots.simulation.CellContent;
import uk.ac.hw.pm190.coralbots.simulation.CellNotEmptyException;
import uk.ac.hw.pm190.coralbots.simulation.Coral;
import uk.ac.hw.pm190.coralbots.simulation.Location;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * Test class for Cell class
 * @author Patrick Mackinder
 */
@RunWith(DataProviderRunner.class)
public class CellTest
{
	@DataProvider
	public static Object[][] notEmptyCellContents()
	{
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new CellContent[] { new Coral() });
		data.add(new CellContent[] { new Rock() });
		return data.toArray(new Object[][] {});
	}

	/**
	 * Test CellNotEmptyException being thrown in appropriate situation
	 * @param content
	 * @throws CellNotEmptyException
	 */
	@Test(expected = CellNotEmptyException.class)
	@UseDataProvider("notEmptyCellContents")
	public void setContentsOfNotEmptyCell_throwsEsception_succeeds(CellContent content) throws CellNotEmptyException
	{
		Cell c = new Cell(new Location(1, 1, 1));
		c.setContents(content);
		c.setContents(content);
	}

	/**
	 * Test that CellNotEmptyException is not thrown when valid call is made
	 * @param content
	 * @throws CellNotEmptyException
	 */
	@Test
	@UseDataProvider("notEmptyCellContents")
	public void setContentsOfEmptyCell_noExcpetion_succeeds(CellContent content) throws CellNotEmptyException
	{
		new Cell(new Location(1, 1, 1)).setContents(content);
	}
}
