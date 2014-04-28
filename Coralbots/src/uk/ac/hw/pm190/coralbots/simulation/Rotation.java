package uk.ac.hw.pm190.coralbots.simulation;

/**
 * Rotaion enumerations, handles 2D coordinate transformations for each rotation
 * @author Patrick Mackinder
 */
public enum Rotation
{
	NORTH
	{
		@Override
		public XYPair transform(XYPair p)
		{
			return p;
		}
	},
	EAST
	{
		@Override
		public XYPair transform(XYPair p)
		{
			return new XYPair(p.getY(), 2 - p.getX());
		}
	},
	SOUTH
	{
		@Override
		public XYPair transform(XYPair p)
		{
			return new XYPair(2 - p.getX(), 2 - p.getY());
		}
	},
	WEST
	{
		@Override
		public XYPair transform(XYPair p)
		{
			return new XYPair(2 - p.getY(), p.getX());
		}
	};

	public abstract XYPair transform(XYPair p);
}
