package de.mvcturbine.breakout.world.entity;

import java.util.List;

import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityPaddle extends Entity
{
	public static double REFLECT_ANGLE_MIN = -45 / 180d * Math.PI;
	public static double REFLECT_ANGLE_MAX = 45 / 180d * Math.PI;

	private Size2D size;

	public EntityPaddle(World w)
	{
		this(w, new Size2D(5, 0.5));
	}

	public EntityPaddle(World w, Size2D size)
	{
		super(w);
		this.size = size;
	}

	@Override
	public Size2D getSize()
	{
		return this.size;
	}

	public void setSize(Size2D size)
	{
		this.size = size;
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
