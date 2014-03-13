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
		Pattern up = new Pattern(pattern);
		Pattern down = new Pattern(pattern);
		Rule r = new Rule(up, down, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		Rule r2 = new Rule(up, down, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		assertTrue("Rules are equal", r.equals(r2));
	}
	
	@Test
	public void testEquals_fails()
	{
		CellContentType[][] pattern = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern up = new Pattern(pattern);
		Pattern down = new Pattern(pattern);
		Rule r = new Rule(up, down, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		CellContentType[][] pattern2 = new CellContentType[][] {
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern up2 = new Pattern(pattern2);
		Pattern down2 = new Pattern(pattern2);
		Rule r2 = new Rule(up2, down2, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		assertFalse("Rules are not equal", r.equals(r2));
	}
}
