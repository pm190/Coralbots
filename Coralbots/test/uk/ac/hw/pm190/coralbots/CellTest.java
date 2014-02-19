package uk.ac.hw.pm190.coralbots;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * 
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
		data.add(new CellContent[] { new Robot(new Location(1, 1, 1)) });
		return data.toArray(new Object[][] {});
	}

	@Test(expected = CellNotEmptyException.class)
	@UseDataProvider("notEmptyCellContents")
	public void setContentsOfNotEmptyCell_throwsEsception_succeeds(CellContent content) throws CellNotEmptyException
	{
		Cell c = new Cell(new Location(1, 1, 1));
		c.setContents(content);
		c.setContents(content);
	}

	@Test
	@UseDataProvider("notEmptyCellContents")
	public void setContentsOfEmptyCell_noExcpetion_succeeds(CellContent content) throws CellNotEmptyException
	{
		new Cell(new Location(1, 1, 1)).setContents(content);
	}
}
