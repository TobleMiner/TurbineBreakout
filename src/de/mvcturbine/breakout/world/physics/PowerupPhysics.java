package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

/**
 * Physics for powerups
 * 
 * @author tsys
 *
 */
public class PowerupPhysics extends PhysicsModel
{

	/**
	 * Constructs new physics for the powerup {@code e}
	 * 
	 * @param e
	 *            The powerup
	 */
	public PowerupPhysics(Entity e)
	{
		super(e);
	}

	@Override
	public void apply()
	{
		EntityPowerup powerup = (EntityPowerup) this.entity;
		WorldBreakout w = (WorldBreakout) powerup.getWorld();
		EntityPaddle paddle = w.getPaddle();
		// Don't do anything if powerup doesn't intersect with paddle
		if(!powerup.getBounds().intersects(paddle.getBounds())) return;
		powerup.remove(true);
		switch(powerup.getEffect())
		{
			case BALL_BREAKTHROUGH:
				w.getBall().setBreakthrough(true);
				break;
			// case BALL_EXPLOSIVE:
			// break;
			// case BALL_MULTI:
			// break;
			case PADDLE_SMALLER:
				double width = paddle.getSize().width;
				width -= EntityPaddle.WIDTH_STEP;
				width = Math.max(width, EntityPaddle.WIDTH_MIN);
				paddle.setSize(new Size2D(width, paddle.getSize().height));
				break;
			case PADDLE_LARGER:
				width = paddle.getSize().width;
				width += EntityPaddle.WIDTH_STEP;
				width = Math.min(width, EntityPaddle.WIDTH_MAX);
				paddle.setSize(new Size2D(width, paddle.getSize().height));
				break;
			case PADDLE_LAZ0R:
				paddle.setArmed(true);
				break;
			// case PADDLE_STICKY:
			// paddle.setSticky(true);
			// break;
			default:
				break;
		}
	}

}
