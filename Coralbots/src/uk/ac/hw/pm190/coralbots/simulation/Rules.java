package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
	public static boolean isValid(File rulesSchema, File rulesSource)
	{
		try 
		{
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(rulesSchema));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(rulesSource));
		} 
		catch (SAXException | IOException e) 
		{
			return false;
		}
		return true;
	}
	
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
		CellContentType[][] upperPatternCells;
		CellContentType[][] lowerPatternCells;
		for(int i = 0; i < nList.getLength(); i++)
		{
			Element rule = (Element)nList.item(i);
			Element patternNode = (Element) rule.getElementsByTagName("pattern").item(0);
			
			Element upperPatternNode = (Element) patternNode.getElementsByTagName("upper").item(0);
			upperPatternCells = new CellContentType[3][3];
			upperPatternCells[0][0] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("topLeft").item(0).getTextContent().toUpperCase().toUpperCase());
			upperPatternCells[1][0] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("top").item(0).getTextContent().toUpperCase());
			upperPatternCells[2][0] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("topRight").item(0).getTextContent().toUpperCase());
			upperPatternCells[0][1] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("middleLeft").item(0).getTextContent().toUpperCase());
			upperPatternCells[1][1] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("middle").item(0).getTextContent().toUpperCase());
			upperPatternCells[2][1] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("middleRight").item(0).getTextContent().toUpperCase());
			upperPatternCells[0][2] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("bottomLeft").item(0).getTextContent().toUpperCase());
			upperPatternCells[1][2] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("bottom").item(0).getTextContent().toUpperCase());
			upperPatternCells[2][2] = CellContentType.valueOf(upperPatternNode.getElementsByTagName("bottomRight").item(0).getTextContent().toUpperCase());
			Pattern upperPattern = new Pattern(upperPatternCells);
			
			Element lowerPatternNode = (Element) patternNode.getElementsByTagName("lower").item(0);
			lowerPatternCells = new CellContentType[3][3];
			lowerPatternCells[0][0] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("topLeft").item(0).getTextContent().toUpperCase().toUpperCase());
			lowerPatternCells[1][0] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("top").item(0).getTextContent().toUpperCase());
			lowerPatternCells[2][0] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("topRight").item(0).getTextContent().toUpperCase());
			lowerPatternCells[0][1] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("middleLeft").item(0).getTextContent().toUpperCase());
			lowerPatternCells[1][1] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("middle").item(0).getTextContent().toUpperCase());
			lowerPatternCells[2][1] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("middleRight").item(0).getTextContent().toUpperCase());
			lowerPatternCells[0][2] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("bottomLeft").item(0).getTextContent().toUpperCase());
			lowerPatternCells[1][2] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("bottom").item(0).getTextContent().toUpperCase());
			lowerPatternCells[2][2] = CellContentType.valueOf(lowerPatternNode.getElementsByTagName("bottomRight").item(0).getTextContent().toUpperCase());
			Pattern lowerPattern = new Pattern(lowerPatternCells);
			
			Element change = (Element) rule.getElementsByTagName("change").item(0);
			PatternCellLocation location = PatternCellLocation.valueOf(change.getElementsByTagName("cell").item(0).getTextContent().toUpperCase());
			CellContentType content = CellContentType.valueOf(change.getElementsByTagName("type").item(0).getTextContent().toUpperCase());
			rules.add(new Rule(upperPattern, lowerPattern, location, content));
		}
		return rules;
	}
	
	public static void writeRules(File rulesSchema, File rulesSource, Collection<Rule> rules)
	{
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("rules");
			doc.appendChild(rootElement);
			for(Rule r : rules)
			{
				Element rule = doc.createElement("rule");
				Element pattern = doc.createElement("pattern");
				Element upper = doc.createElement("upper");
				Element lower = doc.createElement("lower");
				Element change = doc.createElement("change");
				Element cell = doc.createElement("cell");
				Element type = doc.createElement("type");
				
				createPattern(doc, upper, r.getUpperPattern());
				createPattern(doc, lower, r.getLowerPattern());
				
				pattern.appendChild(upper);
				pattern.appendChild(lower);
				
				cell.appendChild(doc.createTextNode(r.getCellToChange().toString()));
				type.appendChild(doc.createTextNode(r.getChangeType().toString()));
				
				change.appendChild(cell);
				change.appendChild(type);
				
				rule.appendChild(pattern);
				rule.appendChild(change);
				
				rootElement.appendChild(rule);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(rulesSource);
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(source, result);
			if(!isValid(new File("resources/rules.xsd"), rulesSource))
			{
				//TODO some error message, should never happen
			}
		}
		catch(ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(TransformerConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createPattern(Document doc, Element element, Pattern pattern)
	{
		for(PatternCellLocation loc : PatternCellLocation.values())
		{
			Element location = doc.createElement(loc.toString());
			location.appendChild(doc.createTextNode(pattern.getCellContentType(new XYPair(loc.getX()+1, loc.getY()+1)).toString()));
			element.appendChild(location);
		}
	}
}
