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
		public WorldAttributeVisitor createVisitor(World world)
		{
			return new FrequencyVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1);
		}
	},
	CORAL
	{
		public WorldAttributeVisitor createVisitor(World world)
		{
			return new CoralVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1);
		}
	},
	ROCK
	{
		public WorldAttributeVisitor createVisitor(World world)
		{
			return new RockVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1);
		}
	},
	REEF
	{
		public WorldAttributeVisitor createVisitor(World world)
		{
			return new ReefVisitor(world.getEnd().getX()+1, world.getEnd().getY()+1);
		}
	};
	
	
	public abstract WorldAttributeVisitor createVisitor(World world);
}
