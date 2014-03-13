package uk.ac.hw.pm190.coralbots.graphics;

import uk.ac.hw.pm190.coralbots.simulation.World;

/**
 * 
 * @author Patrick Mackinder
 */
public enum WorldAttribute
{
	FREQUENCY
	{
		public WorldAttributeDisplay createVisitor(World world)
		{
			return new FrequencyVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1, FREQUENCY);
		}
	},
	CORAL
	{
		public WorldAttributeDisplay createVisitor(World world)
		{
			return new CoralVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1, CORAL);
		}
	};
	
	public abstract WorldAttributeDisplay createVisitor(World world);
}
