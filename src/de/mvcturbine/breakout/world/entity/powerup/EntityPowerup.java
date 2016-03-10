package de.mvcturbine.breakout.world.entity.powerup;

import de.mvcturbine.breakout.world.entity.RoundBoundingBox;
import de.mvcturbine.breakout.world.physics.PowerupPhysics;
import de.mvcturbine.util.geom.EntityBB.Moving;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityPowerup extends MovingEntity
{
	private Powerup effect;

	public EntityPowerup(World w)
	{
		super(w);
		this.physics = new PowerupPhysics(this);
		this.setVelocity(new Vec2D());
	}

	@Override
	public Size2D getSize()
	{
		return new Size2D(0.75, 0.75);
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

	/**
	 * @return the effect
	 */
	public Powerup getEffect()
	{
		return effect;
	}

	/**
	 * @param effect
	 *            the effect to set
	 */
	public void setEffect(Powerup effect)
	{
		this.effect = effect;
	}

	@Override
	public Moving getBounds()
	{
		return new RoundBoundingBox(this);
	}
}
