package de.mvcturbine.breakout.world.entity.fx;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityLaz0rBeam extends MovingEntity
{
	private Size2D size = new Size2D(0.2, 1.414);

	public static double relLaz0rSpeed = 1;

	public EntityLaz0rBeam(World w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		return this.size;
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
