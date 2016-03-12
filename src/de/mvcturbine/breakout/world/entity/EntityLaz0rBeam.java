package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

/**
 * A laser beam shot by the paddle
 * 
 * @author tsys
 *
 */
public class EntityLaz0rBeam extends MovingEntity
{
	/** The size */
	private Size2D size = new Size2D(0.2, 1.414);

	/** Speed of the laser beam relative to the world height */
	public static double relLaz0rSpeed = 1;

	/**
	 * Constructs a new laser beam in {@code w}
	 * 
	 * @param w
	 */
	public EntityLaz0rBeam(World w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		return this.size;
	}

	@Override
	public boolean isSolid()
	{
		return false;
	}

	@Override
	public boolean visible()
	{
		return true;
	}

}
