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

		int endX = world.getEnd().getX();
		int endY = world.getEnd().getY();
		int endZ = world.getEnd().getZ();
		Color black = new Color(0,0,0);
		for(int x = 0; x <= endX; x++)
		{
			for(int y = 0; y <= endY; y++)
			{
				int colVis = columnVisited(x, y, world, endZ);
				if(colVis != 0)
				{
					g.setColor(new Color(Math.min(255, colVis*10), 0, 0));
				}
				else
				{
					g.setColor(new Color(0, 0, 255));
				}
				g.fillRect((x*10), (y*10), 10, 10);
				g.setColor(black);
				g.drawRect((x*10), (y*10), 10, 10);
			}
		}
	}
	
	public int columnVisited(int x, int y, World world, int endZ)
	{
		int total = 0;
		for(int z = 0; z <= endZ; z++)
		{
			total += world.getCell(new Location(x, y, z)).getVisitedCount();
		}
		return total;
	}
}
