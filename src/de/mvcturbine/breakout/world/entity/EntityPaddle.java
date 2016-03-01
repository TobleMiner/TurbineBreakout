package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityPaddle extends Entity
{

	public EntityPaddle(World w)
	{
		super(w);
		this.size = new Size2D(6d, 2d);
	}

}
