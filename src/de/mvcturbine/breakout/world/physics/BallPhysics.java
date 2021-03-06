package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.EntityBB;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.entity.BoundEntity;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

/**
 * Physics of a the ball
 * 
 * @author tsys
 *
 */
public class BallPhysics extends PhysicsModel
{
	/**
	 * Creates new physics for the {@code ball}
	 * 
	 * @param ball
	 */
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
			if(ent == ball) continue; // Continue if checking against same
										// entity
			if(!ent.isSolid()) continue; // Continue if other entity isn't solid
			BoundingBox entbb = ent.getBounds();

			if(!bbb.intersects(entbb)) continue; // Continue if entities don't
													// intersect
			if(ent instanceof BoundEntity) w.combobreaker(); // Reset score
																// multiplier if
																// world
																// boundary was
																// hit
			if(ent instanceof EntityPaddle) // Calculate reflection angle for
											// pedal
			{
				double angleDelta = EntityPaddle.REFLECT_ANGLE_MAX -
						EntityPaddle.REFLECT_ANGLE_MIN;
				Loc2D midpoint = ball.getLocation()
						.add(new Vec2D(ball.getSize()).divide(2));
				EntityPaddle paddle = w.getPaddle();
				double paddleDelta = midpoint.getX() - paddle.getLocation().getX();
				double angle = EntityPaddle.REFLECT_ANGLE_MIN +
						angleDelta * (paddleDelta / paddle.getSize().width);
				angle = Math.max(angle, EntityPaddle.REFLECT_ANGLE_MIN);
				angle = Math.min(angle, EntityPaddle.REFLECT_ANGLE_MAX);
				ball.setVelocity(ball.getVelocity().clone()
						.setAngle(new Vec2D(1, 0).getAngle() + Math.PI / 2 - angle));
			}
			else
			{
				// Ignore blocks if ball is break through
				if(ball.isBreakthrough() && (ent instanceof EntityBlock)) continue;
				double angle = bbb.getCollisionAngle(entbb);
				if(!Double.isNaN(angle))
				{
					ball.setVelocity(ball.getVelocity().clone().setAngle(angle));
					Vec2D ballVelocity = ball.getVelocity().clone();
					double yxQuotient = ballVelocity.getY() / ballVelocity.getX();
					// Don't touch ball angle if y component of vector is big
					// enough
					if(Math.abs(yxQuotient) >= EntityBall.MIN_REL_Y_SPEED) continue;
					Vec2D newVelocity = new Vec2D(ballVelocity.getX(),
							ballVelocity.getY() *
									(EntityBall.MIN_REL_Y_SPEED / yxQuotient));
					newVelocity.multiply(ballVelocity.length() / newVelocity.length());
					ball.setVelocity(newVelocity);
				}
			}
		}
	}
}
