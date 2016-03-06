package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityPaddle extends Entity
{
	public EntityPaddle(World w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		return new Size2D(6, 2);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}
}
