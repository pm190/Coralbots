package uk.ac.hw.pm190.coralbots.simulation;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * 
 * @author Patrick Mackinder
 */
public class RulesTest
{
	@Test
	public void parseRules_validFile_succeeds() throws SAXException, IOException, ParserConfigurationException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/validRules.xml");
		
		Collection<Rule> rules = Rules.parseRulesFile(schema, source);
		assertTrue("Correct number of rules", rules.size()==1);
	}
	
	@Test(expected = SAXException.class)
	public void parseRules_invalidFile_fails() throws SAXException, IOException, ParserConfigurationException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/invalidRules.xml");
		
		Rules.parseRulesFile(schema, source);
	}
	
	@Test
	public void parseRules_validFile_knownPattern_succeeds() throws SAXException, IOException, ParserConfigurationException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/knownRules.xml");
		
		CellContentType[][] pattern = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern up = new Pattern(pattern);
		Pattern down = new Pattern(pattern);
		Rule r = new Rule(up, down, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		Collection<Rule> rules = Rules.parseRulesFile(schema, source);
		assertTrue("Matching pattern", rules.contains(r));
	}
	
	@Test
	public void parseRules_validFile_knownPatternDifferentUpperLower_succeeds() throws SAXException, IOException, ParserConfigurationException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/differentUpperLower.xml");
		
		CellContentType[][] upper = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern up = new Pattern(upper);
		
		CellContentType[][] lower = new CellContentType[][] {
				{CellContentType.CORAL, CellContentType.CORAL, CellContentType.CORAL}, 
				{CellContentType.CORAL, CellContentType.CORAL, CellContentType.CORAL}, 
				{CellContentType.CORAL, CellContentType.CORAL, CellContentType.CORAL}};
		Pattern down = new Pattern(lower);
		Rule r = new Rule(up, down, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		Collection<Rule> rules = Rules.parseRulesFile(schema, source);
		assertTrue("Matching pattern", rules.contains(r));
	}
}
