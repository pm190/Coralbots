package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.hw.pm190.coralbots.simulation.Pattern;

/**
 * 
 * @author Patrick Mackinder
 */
public class PatternTest
{

	@Test
	public void patternMatches_succeeds()
	{
		CellContentType[][] pattern= new CellContentType[][] {{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, {CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}, {CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p1 = new Pattern(pattern);
		Pattern p2 = new Pattern(pattern);
		assertTrue("Pattern matches", p1.equals(p2));
	}
	
	@Test
	public void patternMatches_fails()
	{
		CellContentType[][] pattern1 = new CellContentType[][] {{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, {CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}, {CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		CellContentType[][] pattern2 = new CellContentType[][] {{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, {CellContentType.CORAL, CellContentType.CORAL, CellContentType.CORAL}, {CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p1 = new Pattern(pattern1);
		Pattern p2 = new Pattern(pattern2);
		assertFalse("Pattern matches", p1.equals(p2));
	}
	
	@Test
	public void patternMatch_90degrees_succeeds()
	{
		CellContentType[][] pattern1 = new CellContentType[][] 
				{{CellContentType.WATER, CellContentType.CORAL, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}};
		
		CellContentType[][] pattern2 = new CellContentType[][] 
				{{CellContentType.CORAL, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.CORAL}, 
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.WATER}};
		
		Pattern p1 = new Pattern(pattern1);
		Pattern p2 = new Pattern(pattern2);
		assertTrue("Pattern matches", p1.equals(p2));
	}
	
	@Test
	public void patternMatch_180degrees_succeeds()
	{
		CellContentType[][] pattern1 = new CellContentType[][] 
				{{CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.CORAL, CellContentType.WATER}};
		
		CellContentType[][] pattern2 = new CellContentType[][] 
				{{CellContentType.WATER, CellContentType.CORAL, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}};
		
		Pattern p1 = new Pattern(pattern1);
		Pattern p2 = new Pattern(pattern2);
		assertTrue("Pattern matches", p1.equals(p2));
	}
	
	@Test
	public void patternMatch_270degrees_succeeds()
	{
		CellContentType[][] pattern1 = new CellContentType[][] 
				{{CellContentType.WATER, CellContentType.CORAL, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.CORAL}};
		
		CellContentType[][] pattern2 = new CellContentType[][] 
				{{CellContentType.WATER, CellContentType.WATER, CellContentType.CORAL}, 
				{CellContentType.CORAL, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.CORAL}};
		
		Pattern p1 = new Pattern(pattern1);
		Pattern p2 = new Pattern(pattern2);
		assertTrue("Pattern matches", p1.equals(p2));
	}
}
