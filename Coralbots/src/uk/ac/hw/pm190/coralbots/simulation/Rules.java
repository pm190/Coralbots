package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Rules
{
	public static Collection<Rule> parseRulesFile(File rulesSchema, File rulesSource) throws SAXException, IOException
	{
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new StreamSource(rulesSchema));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(rulesSource));
        return new ArrayList<Rule>();
	}
}
