package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.physics.BallPhysics;
import de.mvcturbine.util.geom.EntityBB.Moving;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

/**
 * The ball in a breakout game
 * 
 * @author tsys
 *
 */
public class EntityBall extends MovingEntity
{
	/** true if ball should just break through blocks */
	private boolean breakthrough = false;

	public EntityBall(World w)
	{
		super(w);
		this.physics = new BallPhysics(this);
	}

	@Override
	public Moving getBounds()
	{
		return new RoundBoundingBox(this);
	}

	@Override
	public Size2D getSize()
	{
		return new Size2D(1, 1);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}

	@Override
	public boolean visible()
	{
		return true;
	}

	/**
	 * Returns true if ball should break through blocks
	 * 
	 * @return the breakthrough
	 */
	public boolean isBreakthrough()
	{
		return breakthrough;
	}

	/**
	 * Sets whether this ball should break through blocks
	 * 
	 * @param breakthrough
	 *            the breakthrough to set
	 */
	public void setBreakthrough(boolean breakthrough)
	{
		this.breakthrough = breakthrough;
	}
}
