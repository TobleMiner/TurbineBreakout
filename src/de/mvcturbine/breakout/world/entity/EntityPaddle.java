package de.mvcturbine.breakout.world.entity;

import java.util.List;

import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
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
		return new Size2D(5, 0.5);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}

	public boolean canMoveTo(Loc2D loc)
	{
		Vec2D delta = new Vec2D(loc.clone().sub(this.getLocation()));
		List<Entity> entities = getCollidingEntities(delta);
		return entities.isEmpty();
	}

	@Override
	public boolean visible()
	{
		return true;
	}
}
