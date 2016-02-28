package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.world.World;
import de.mvcturbine.world.physics.PhysicsModel;

public class BallPhysics extends PhysicsModel
{
	protected EntityBall entity;

	public BallPhysics(EntityBall ball)
	{
		super(ball);
	}

	@Override
	public void apply()
	{
		World w = this.entity.getWorld();
		w.getBounds();
	}
}
