package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleTest
{
	@Test
	public void testEquals_succeeds()
	{
		CellContentType[][] pattern = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p = new Pattern(pattern);
		Rule r = new Rule(p, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		Rule r2 = new Rule(p, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		assertTrue("Rules are equal", r.equals(r2));
	}
	
	@Test
	public void testEquals_fails()
	{
		CellContentType[][] pattern = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p = new Pattern(pattern);
		Rule r = new Rule(p, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		CellContentType[][] pattern2 = new CellContentType[][] {
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p2 = new Pattern(pattern2);
		Rule r2 = new Rule(p2, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		assertFalse("Rules are not equal", r.equals(r2));
	}
}
