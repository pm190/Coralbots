package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * 
 * @author Patrick Mackinder
 */
public class RulesTest
{
	@Test
	public void parseRules_validFile_succeeds() throws SAXException, IOException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/validRules.xml");
		
		Rules.parseRulesFile(schema, source);
	}
	
	@Test(expected = SAXException.class)
	public void parseRules_invalidFile_fails() throws SAXException, IOException
	{
		File schema = new File("resources/rules.xsd");
		File source = new File("test/resources/invalidRules.xml");
		
		Rules.parseRulesFile(schema, source);
	}
}
