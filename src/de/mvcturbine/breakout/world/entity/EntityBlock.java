package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.breakout.world.physics.BlockPhysics;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityBlock extends Entity {
	public static int MAX_DURABILITY = 4;

	private int durability;

	private final int initialDurability;

	private final Size2D size;

	private EntityPowerup powerup;

	public EntityBlock(World w) {
		this(w, 1, new Size2D(2, 1));
	}

	public EntityBlock(World w, Size2D size) {
		this(w, 1, size);
	}

	public EntityBlock(World w, int durability) {
		this(w, 1, new Size2D(2, durability));
	}

	public EntityBlock(World w, int durability, Size2D size) {
		super(w);
		this.physics = new BlockPhysics(this);
		this.durability = durability;
		this.initialDurability = durability;
		this.size = size;
	}

	@Override
	public Size2D getSize() {
		return this.size;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean visible() {
		return true;
	}

	/**
	 * @return the durability
	 */
	public int getDurability() {
		return this.durability;
	}

	/**
	 * @param durability
	 *            the durability to set
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}

	public void hit() {
		this.durability--;
		if (this.durability == 0)
			this.remove(true);
	}

	public void destroy() {
		this.durability = 0;
		this.remove(true);
		if (this.powerup != null)
			this.powerup.remove(true);
	}

	/**
	 * @return the powerup
	 */
	public EntityPowerup getPowerup() {
		return this.powerup;
	}

	/**
	 * @param powerup
	 *            the powerup to set
	 */
	public void setPowerup(EntityPowerup powerup) {
		this.powerup = powerup;
	}

	public int getInitialDurability() {
		return initialDurability;
	}
}
