package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.EntityBB;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.entity.BoundEntity;
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
			if(ent == ball) continue;
			if(ent.isSolid())
			{
				if(ent instanceof BoundEntity) w.combobreaker();
				BoundingBox entbb = ent.getBounds();

				if(bbb.intersects(entbb))
				{
					if(ent instanceof EntityPaddle)
					{
						double angleDelta = EntityPaddle.REFLECT_ANGLE_MAX -
								EntityPaddle.REFLECT_ANGLE_MIN;
						Loc2D midpoint = ball.getLocation()
								.add(new Vec2D(ball.getSize()).divide(2));
						EntityPaddle paddle = w.getPaddle();
						double paddleDelta = midpoint.getX() -
								paddle.getLocation().getX();
						double angle = EntityPaddle.REFLECT_ANGLE_MIN +
								angleDelta * (paddleDelta / paddle.getSize().width);
						angle = Math.max(angle, EntityPaddle.REFLECT_ANGLE_MIN);
						angle = Math.min(angle, EntityPaddle.REFLECT_ANGLE_MAX);
						ball.setVelocity(ball.getVelocity().clone().setAngle(
								new Vec2D(1, 0).getAngle() + Math.PI / 2 - angle));
					}
					else
					{
						double angle = bbb.getCollisionAngle(entbb);
						if(!Double.isNaN(angle))
						{
							ball.setVelocity(ball.getVelocity().clone().setAngle(angle));
						}
					}
				}
			}
		}
	}
}
