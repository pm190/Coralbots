package uk.ac.hw.pm190.coralbots.simulation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * 
 * @author Patrick Mackinder
 */
public interface RobotFactory
{
	public List<Robot> createRobots(int numberOfRobots, Location worldEnd, File rules) throws SAXException, IOException, ParserConfigurationException;
}
