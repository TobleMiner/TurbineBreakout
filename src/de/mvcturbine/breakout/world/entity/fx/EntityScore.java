package de.mvcturbine.breakout.world.entity.fx;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class EntityScore extends Entity
{
	private final Size2D size = new Size2D(3, 1);

	public EntityScore(WorldBreakout w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		return size;
	}

	@Override
	public boolean isSolid()
	{
		return false;
	}

	@Override
	public boolean visible()
	{
		return true;
	}

	@Override
	public WorldBreakout getWorld()
	{
		return (WorldBreakout) super.getWorld();
	}

}
