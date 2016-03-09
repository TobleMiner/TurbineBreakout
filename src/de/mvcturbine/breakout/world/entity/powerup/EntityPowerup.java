package de.mvcturbine.breakout.world.entity.powerup;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityPowerup extends MovingEntity
{

	protected EntityPowerup(World w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		return new Size2D(1, 1);
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

}
