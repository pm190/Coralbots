package uk.ac.hw.pm190.coralbots.graphics;

import java.util.ArrayList;
import java.util.List;

import uk.ac.hw.pm190.coralbots.simulation.World;

public class WorldImage
{
	private final World world;
	private final WorldAttribute[] attributes;
	private final List<WorldAttributeVisitor> panels = new ArrayList<WorldAttributeVisitor>();

	public WorldImage(World world, WorldAttribute ... attributes)
	{
		this.world = world;
		this.attributes = attributes;
	}

	public void visitAttributes()
	{
		int endX = world.getEnd().getX();
		int endY = world.getEnd().getY();

		WorldAttributeVisitor wad;
		for(WorldAttribute attribute : attributes)
		{
			wad = attribute.createVisitor(world);
			for(int x = 0; x <= endX; x++)
			{
				for(int y = 0; y <= endY; y++)
				{
					wad.visit(x, y, world.getColumn(x, y));
				}
			}
			panels.add(wad);
		}
	}

	public List<WorldAttributeVisitor> getPanels()
	{
		return panels;
	}
	
	public World getWorld()
	{
		return world;
	}
}
