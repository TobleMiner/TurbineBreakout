package de.mvcturbine.breakout.world.entity;

import java.util.List;

import de.mvcturbine.breakout.world.entity.fx.EntityLaz0rBeam;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityPaddle extends Entity
{
	public static double REFLECT_ANGLE_MIN = -45 / 180d * Math.PI;
	public static double REFLECT_ANGLE_MAX = 45 / 180d * Math.PI;

	public static double WIDTH_MAX = 5;
	public static double WIDTH_MIN = 1;

	public static double WIDTH_STEP = 1;

	private Size2D size;

	private boolean armed = false;
	private boolean sticky = false;

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

	/**
	 * @return the armed
	 */
	public boolean isArmed()
	{
		return armed;
	}

	/**
	 * @param armed
	 *            the armed to set
	 */
	public void setArmed(boolean armed)
	{
		this.armed = armed;
	}

	public void shoot()
	{
		EntityLaz0rBeam laz0rBeam = new EntityLaz0rBeam(this.world);
		laz0rBeam.setLocation(
				this.getLocation().clone().add(new Vec2D(0, getSize().height)));
		laz0rBeam.setVelocity(new Vec2D(0,
				this.world.getSize().getHeight() * EntityLaz0rBeam.relLaz0rSpeed));
		this.world.addEntity(laz0rBeam);
		laz0rBeam = new EntityLaz0rBeam(this.world);
		laz0rBeam.setLocation(this.getLocation().clone().add(new Vec2D(
				getSize().width - laz0rBeam.getSize().width, getSize().height)));
		laz0rBeam.setVelocity(new Vec2D(0,
				this.world.getSize().getHeight() * EntityLaz0rBeam.relLaz0rSpeed));
		this.world.addEntity(laz0rBeam);
	}

	/**
	 * @return the sticky
	 */
	public boolean isSticky()
	{
		return sticky;
	}

	/**
	 * @param sticky
	 *            the sticky to set
	 */
	public void setSticky(boolean sticky)
	{
		this.sticky = sticky;
	}
}
