package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.fx.EntityLaz0rBeam;
import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.EntityBB;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

public class BlockPhysics extends PhysicsModel
{

	public BlockPhysics(EntityBlock block)
	{
		super(block);
	}

	@Override
	public void apply()
	{
		EntityBlock block = (EntityBlock) this.entity;
		EntityBB blbb = block.getBounds();
		WorldBreakout w = (WorldBreakout) block.getWorld();
		for(Entity e : w.getAllEntities())
		{
			if(e instanceof EntityBall)
			{
				EntityBall ball = (EntityBall) e;
				BoundingBox babb = ball.getBounds();

				if(!babb.intersects(blbb)) continue;
				block.hit();
				if(block.getDurability() == 0)
				{
					w.scoreBreak();
					EntityPowerup powerup = block.getPowerup();
					if(powerup != null)
					{
						powerup.setVelocity(new Vec2D(0,
								w.getPowerUpFallTime() * -w.getSize().height));
					}
				}
				else
					w.scoreHit();
			}
			else if(e instanceof EntityLaz0rBeam)
			{
				if(e.getBounds().intersects(blbb))
				{
					block.destroy();
					w.scoreHit();
					e.remove(true);
				}
			}
		}
	}
}
