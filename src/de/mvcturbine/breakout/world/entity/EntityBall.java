package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.physics.BallPhysics;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityBall extends MovingEntity
{

	public EntityBall(World w)
	{
		super(w);
		this.physics = new BallPhysics(this);
		this.size = new Size2D(1, 1);
	}
}
