package uk.ac.hw.pm190.coralbots.simulation;

import java.util.Random;

/**
 * 
 * @author Patrick Mackinder
 */
public class Location
{
	private final int x, y, z;

	public Location(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Create location relative to another location
	 * @param location
	 * @param x offset
	 * @param y offset
	 * @param z pffset
	 */
	public Location(Location location, int x, int y, int z)
	{
		this.x = location.x + x;
		this.y = location.y + y;
		this.z = location.z + z;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getZ()
	{
		return z;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		if(z != other.z)
			return false;
		return true;
	}

	public boolean isNeighbour(Location other)
	{
		if((Math.abs(other.getX() - this.getX()) <= 1) && (Math.abs(other.getY() - this.getY()) <= 1) && (Math.abs(other.getZ() - this.getZ()) <= 1))
		{
			return true;
		}
		return false;
	}

	public static Location getMiddle(Location a, Location b)
	{
		return new Location(Math.abs((b.getX() - a.getX()) / 2), Math.abs((b.getY() - a.getY()) / 2), Math.abs((b.getZ() - a.getZ()) / 2));
	}

	public static Location randomLocation(Location end)
	{
		Random random = new Random();
		return new Location(random.nextInt(end.getX() + 1), random.nextInt(end.getY() + 1), random.nextInt(end.getZ() + 1));
	}

	public Location randomLocation(Location end, int distance)
	{
		Random random = new Random();
		int x = Math.max(0, Math.min(getX() + (random.nextInt((distance * 2)+1) - distance), end.getX()));
		int y = Math.max(0, Math.min(getY() + (random.nextInt((distance * 2)+1) - distance), end.getY()));
		int z = Math.max(0, Math.min(getZ() + (random.nextInt((distance * 2)+1) - distance), end.getZ()));
		return new Location(x, y, z);
	}
}
