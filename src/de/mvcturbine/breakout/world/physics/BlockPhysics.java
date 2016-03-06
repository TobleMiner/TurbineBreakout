package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.EntityBB;
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
		WorldBreakout w = (WorldBreakout) block.getWorld();
		EntityBB blbb = block.getBounds();
		EntityBall ball = w.getBall();
		BoundingBox babb = ball.getBounds();

		if(babb.intersects(blbb))
		{
			block.remove(true);
		}
	}

}
