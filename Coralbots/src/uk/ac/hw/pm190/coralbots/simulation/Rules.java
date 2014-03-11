package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Rules
{
	public static Collection<Rule> parseRulesFile(File rulesSchema, File rulesSource) throws SAXException, IOException, ParserConfigurationException
	{
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new StreamSource(rulesSchema));
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(rulesSource));

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(rulesSource);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("rule");
		
		Collection<Rule> rules = new ArrayList<Rule>();
		for(int i = 0; i < nList.getLength(); i++)
		{
			Element rule = (Element)nList.item(i);

			Element patternNode = (Element) rule.getElementsByTagName("pattern").item(0);
			CellContentType[][] patternCells = new CellContentType[3][3];
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("topLeft").item(0).getTextContent().toUpperCase().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("top").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("topRight").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("middleLeft").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("middle").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("middleRight").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("bottomLeft").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("bottom").item(0).getTextContent().toUpperCase());
			patternCells[0][0] = CellContentType.valueOf(patternNode.getElementsByTagName("bottomRight").item(0).getTextContent().toUpperCase());
			Pattern pattern = new Pattern(patternCells);
			
			Element change = (Element) rule.getElementsByTagName("change").item(1);
			PatternCellLocation location = PatternCellLocation.valueOf(change.getElementsByTagName("cell").item(0).getTextContent().toUpperCase());
			CellContentType content = CellContentType.valueOf(change.getElementsByTagName("changeTo").item(0).getTextContent().toUpperCase());
			rules.add(new Rule(pattern, location, content));
		}
		return rules;
	}
}
