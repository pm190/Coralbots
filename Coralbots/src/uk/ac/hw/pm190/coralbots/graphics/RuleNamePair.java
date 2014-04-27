package uk.ac.hw.pm190.coralbots.graphics;

import uk.ac.hw.pm190.coralbots.simulation.Rule;

/**
 * 
 * @author Patrick Mackinder
 */
public class RuleNamePair
{
	private final String name;
	private final Rule rule;
	
	public RuleNamePair(String name, Rule rule)
	{
		this.name = name;
		this.rule = rule;
	}

	public String getName()
	{
		return name;
	}

	public Rule getRule()
	{
		return rule;
	}
	
	public String toString()
	{
		return name;
	}
}
