package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.EntityBB;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

public class BallPhysics extends PhysicsModel
{
	public BallPhysics(EntityBall ball)
	{
		super(ball);
	}

	@Override
	public void apply()
	{
		EntityBall ball = (EntityBall) this.entity;
		WorldBreakout w = (WorldBreakout) ball.getWorld();
		EntityBB.Moving bbb = ball.getBounds();

		for(Entity ent : w.getAllEntities())
		{
			if(ent.isSolid())
			{

				BoundingBox entbb = ent.getBounds();

				if(bbb.intersects(entbb))
				{
					double angle = bbb.getCollisionAngle(entbb);
					if(!Double.isNaN(angle))
					{
						ball.setVelocity(ball.getVelocity().setAngle(angle));
					}
				}
			}
		}
	}
}
