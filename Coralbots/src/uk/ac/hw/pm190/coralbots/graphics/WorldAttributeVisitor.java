package uk.ac.hw.pm190.coralbots.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Patrick Mackinder
 */
public abstract class WorldAttributeVisitor extends JPanel implements ColumnVisitor
{
	private static final long serialVersionUID = 1L;
	private final Color[][] colors;
	private final int sizex;
	private final int sizey;
	private final WorldAttribute attribute;

	public WorldAttributeVisitor(int sizex, int sizey, WorldAttribute attribute)
	{
		this.sizex = sizex;
		this.sizey = sizey;
		colors = new Color[sizex][sizey];
		this.attribute = attribute;
		setPreferredSize(new Dimension(sizex*10, sizey*10));
	}
	
	public Color getColor(int x, int y)
	{
		return colors[x][y];
	}
	
	public void setColor(int x, int y, Color color)
	{
		colors[x][y] = color;
	}

	public int getSizeX()
	{
		return sizex;
	}

	public int getSizeY()
	{
		return sizey;
	}
	
	@Override
	public void paint(Graphics g)
	{
		int sizeX = getSizeX();
		int sizeY = getSizeY();
		for(int x = 0; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				g.setColor(getColor(x,y));
				g.fillRect((x*10), (y*10), 10, 10);
				g.setColor(new Color(0,0,0));
				g.drawRect((x*10), (y*10), 10, 10);
			}
		}
	}

	public WorldAttribute getAttribute()
	{
		return attribute;
	}
}
