package uk.ac.hw.pm190.coralbots;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Patrick Mackinder
 */
public class PatternTest
{

	@Test
	public void patternMatches_succeeds()
	{
		CellContent[][] pattern= new CellContent[][] {{new Water(), new Water(), new Water()}, {new Coral(), new Water(), new Coral()}, {new Water(), new Water(), new Water()}};
		Pattern p1 = new Pattern(pattern);
		Pattern p2 = new Pattern(pattern);
		assertTrue("Pattern matches", p1.equals(p2));
	}
	
	@Test
	public void patternMatches_fails()
	{
		CellContent[][] pattern1 = new CellContent[][] {{new Water(), new Water(), new Water()}, {new Coral(), new Water(), new Coral()}, {new Water(), new Water(), new Water()}};
		CellContent[][] pattern2 = new CellContent[][] {{new Water(), new Water(), new Water()}, {new Coral(), new Coral(), new Coral()}, {new Water(), new Water(), new Water()}};
		Pattern p1 = new Pattern(pattern1);
		Pattern p2 = new Pattern(pattern2);
		assertFalse("Pattern matches", p1.equals(p2));
	}
}
