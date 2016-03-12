package de.mvcturbine.breakout.world.entity;

import java.util.List;

import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

/**
 * The paddle
 * 
 * @author tsys
 *
 */
public class EntityPaddle extends Entity
{
	/** Minimum reflection agle */
	public static double REFLECT_ANGLE_MIN = -45 / 180d * Math.PI;
	/** Maximum reflection agle */
	public static double REFLECT_ANGLE_MAX = 45 / 180d * Math.PI;

	/** Maximum paddle width */
	public static double WIDTH_MAX = 5;
	/** Minimum paddle width */
	public static double WIDTH_MIN = 1;

	/** Step of paddle resize */
	public static double WIDTH_STEP = 1;

	/** The size */
	private Size2D size;

	/** Equipped with LAZ0RZ? */
	private boolean armed = false;

	/** Is paddle sticky? */
	private boolean sticky = false;

	/**
	 * Constructs a new paddle with default size
	 * 
	 * @param w
	 *            The world
	 */
	public EntityPaddle(World w)
	{
		this(w, new Size2D(5, 0.5));
	}

	/**
	 * Constructs a new paddle
	 * 
	 * @param w
	 *            The world
	 * @param size
	 *            The size
	 */
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

	/**
	 * Sets the size
	 * 
	 * @param size
	 *            the size to set
	 */
	public void setSize(Size2D size)
	{
		this.size = size;
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}

	/**
	 * Checks if moving to {@code loc} would cause a collision wit a solid
	 * object
	 * 
	 * @param loc
	 *            The location
	 * @return true if there is no collision
	 */
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
	 * Returns whether the paddle is armed
	 * 
	 * @return the armed
	 */
	public boolean isArmed()
	{
		return armed;
	}

	/**
	 * Sets the armament status of the paddle
	 * 
	 * @param armed
	 *            the armament status to set
	 */
	public void setArmed(boolean armed)
	{
		this.armed = armed;
	}

	/**
	 * Shoot two laser beams from the paddle
	 */
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
	 * Return whether this paddle is sticky
	 * 
	 * @return the stickiness of the paddle
	 */
	public boolean isSticky()
	{
		return sticky;
	}

	/**
	 * Sets the stickiness of the paddle
	 * 
	 * @param sticky
	 *            the stickiness to set
	 */
	public void setSticky(boolean sticky)
	{
		this.sticky = sticky;
	}
}
