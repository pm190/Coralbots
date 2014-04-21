package uk.ac.hw.pm190.coralbots.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.ac.hw.pm190.coralbots.simulation.Rules;

public class SimulationParameters 
{
	private final String x;
	private int endX;
	private final String y;
	private int endY;
	private final String z;
	private int endZ;
	private final String numberOfRobots;
	private int numRobots;
	private final String rules;
	private File rulesFile;
	private final String cycles;
	private int cyc;
	private final String corals;
	private int cor;
	private final List<String> errors = new ArrayList<String>();
	
	public SimulationParameters(String x, String y, String z, String numberOfRobots, String rules, String cycles, String corals)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.numberOfRobots = numberOfRobots;
		this.rules = rules;
		this.cycles = cycles;
		this.corals = corals;
	}
	
	public boolean validate()
	{
		return (validateCoordinates()
				&& validateNumberOfRobots() 
				&& validateRulesFile()
				&& validateCycles()
				&& validateCorals());
	}
	
	public List<String> getErrors()
	{
		return errors;
	}
	
	private boolean validateCoordinates()
	{
		try
		{
			endX = Integer.parseInt(this.x);
			endY = Integer.parseInt(this.y);
			endZ = Integer.parseInt(this.z);
		}
		catch(NumberFormatException e)
		{
			errors.add("Not a valid integer in end coordinates");
			return false;
		}
		
		if(endX < 9 || endY < 9 || endZ < 9)
		{
			errors.add("Invalid end coordinates, must be at least (9,9,9)");
			return false;
		}
		return true;
	}
	
	private boolean validateNumberOfRobots()
	{
		try
		{
			numRobots = Integer.parseInt(this.numberOfRobots);
		}
		catch(NumberFormatException e)
		{
			errors.add("Not a valid integer for number of robots");
			return false;
		}
		int maxRobots = (((endX+1) * (endY+1) * (endZ+1)) / 20);
		if(numRobots > maxRobots)
		{
			errors.add("Too many robots, max allowed robots for this world size is " + maxRobots);
			return false;
		}
		return true;
	}
	
	private boolean validateRulesFile()
	{
		if(rules == null)
		{
			errors.add("No rules file specified");
			return false;
		}
		File rules = new File(this.rules);
		if(rules.exists())
		{
			if(Rules.isValid(new File("resources/rules.xsd"), rules))
			{
				rulesFile = rules;
				return true;
			}
		}
		errors.add("Invalid rules file");
		return false;
	}
	
	private boolean validateCycles()
	{
		try
		{
			cyc = Integer.parseInt(this.cycles);
		}
		catch(NumberFormatException e)
		{
			errors.add("Not a valid integer for number of cycles");
			return false;
		}
		return (cyc > 0) ? true : false;
	}
	
	private boolean validateCorals()
	{
		try
		{
			cor = Integer.parseInt(this.corals);
		}
		catch(NumberFormatException e)
		{
			errors.add("Not a valid integer for number of corals");
			return false;
		}
		if(cor > ((endX * endY * endZ) / 4))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the endX
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * @return the endY
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * @return the endZ
	 */
	public int getEndZ() {
		return endZ;
	}

	/**
	 * @return the numRobots
	 */
	public int getNumRobots() {
		return numRobots;
	}

	/**
	 * @return the rulesFiles
	 */
	public File getRulesFile() {
		return rulesFile;
	}

	/**
	 * @return the cycles
	 */
	public int getCycles() {
		return cyc;
	}

	/**
	 * @return the corals
	 */
	public int getCorals() {
		return cor;
	}
}
