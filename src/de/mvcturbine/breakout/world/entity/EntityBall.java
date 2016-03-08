package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.physics.BallPhysics;
import de.mvcturbine.util.geom.EntityBB.Moving;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityBall extends MovingEntity
{

	public EntityBall(World w)
	{
		super(w);
		this.physics = new BallPhysics(this);
	}

	@Override
	public Moving getBounds()
	{
		return new BallBoundingBox(this);
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
}
