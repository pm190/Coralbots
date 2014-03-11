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
	public void parseRules_validFile_knownPattern_suceeds() throws SAXException, IOException, ParserConfigurationException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/knownRules.xml");
		
		CellContentType[][] pattern = new CellContentType[][] {
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}, 
				{CellContentType.WATER, CellContentType.WATER, CellContentType.WATER}};
		Pattern p = new Pattern(pattern);
		Rule r = new Rule(p, PatternCellLocation.MIDDLE, CellContentType.CORAL);
		
		Collection<Rule> rules = Rules.parseRulesFile(schema, source);
		assertTrue("Matching pattern", rules.contains(r));
	}
}
