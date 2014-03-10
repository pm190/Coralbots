package uk.ac.hw.pm190.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import uk.ac.hw.pm190.coralbots.Location;
import uk.ac.hw.pm190.coralbots.World;

public class WorldImage extends Canvas
{
	private static final long serialVersionUID = 1L;
	private final World world;

	public WorldImage(World world)
	{
		this.world = world;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		int endY = world.getEnd().getY();
		int endX = world.getEnd().getX();
		int endZ = world.getEnd().getZ();
		for(int x = 0; x < endX; x++)
		{
			for(int y = 0; y < endY; y++)
			{
				if(columnVisited(x, y, world, endZ))
				{
					g.setColor(new Color(255, 0, 0));
				}
				else
				{
					g.setColor(new Color(0, 0, 255));
				}
				g.fillRect((x*10), (y*10), 10, 10);
				
			}
		}
	}
	
	public boolean columnVisited(int x, int y, World world, int endZ)
	{
		for(int z = 0; z < endZ; z++)
		{
			if(world.getCell(new Location(x, y, z)).getVisitedCount() > 0)
			{
				return true;
			}
		}
		return false;
	}
}
