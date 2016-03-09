package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

public class PowerupPhysics extends PhysicsModel
{

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
		if(powerup.getBounds().intersects(paddle.getBounds()))
		{
			powerup.remove(true);
		}
	}

}
