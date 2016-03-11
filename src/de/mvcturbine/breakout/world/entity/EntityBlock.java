package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.breakout.world.physics.BlockPhysics;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

/**
 * A block in breakout
 * 
 * @author tsys
 *
 */
public class EntityBlock extends Entity
{
	/** The maximum durability of a breakout block */
	public static int MAX_DURABILITY = 4;

	/** The actual durability */
	private int durability;

	/** The initial durability */
	private final int initialDurability;

	/** The size */
	private final Size2D size;

	/** Contained powerup */
	private EntityPowerup powerup;

	/**
	 * Constructs a new breakout block in {@code w} with default parameters for
	 * size and durability
	 * 
	 * @param w
	 *            The world
	 */
	public EntityBlock(World w)
	{
		this(w, 1, new Size2D(2, 1));
	}

	/**
	 * Constructs a new breakout block in {@code w} with default for durability
	 * 
	 * @param w
	 *            The world
	 * @param size
	 *            The size
	 */
	public EntityBlock(World w, Size2D size)
	{
		this(w, 1, size);
	}

	/**
	 * Constructs a new breakout block in {@code w} with default for size
	 * 
	 * @param w
	 *            The world
	 * @param durability
	 *            The durability
	 */
	public EntityBlock(World w, int durability)
	{
		this(w, durability, new Size2D(2, 1));
	}

	/**
	 * Constructs a new breakout block in {@code w}
	 * 
	 * @param w
	 *            The world
	 * @param durability
	 *            The durability
	 * @param size
	 *            The size
	 */
	public EntityBlock(World w, int durability, Size2D size)
	{
		super(w);
		this.physics = new BlockPhysics(this);
		this.durability = durability;
		this.initialDurability = durability;
		this.size = size;
	}

	@Override
	public Size2D getSize()
	{
		return this.size;
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}

	@Override
	public boolean visible()
	{
		return true;
	}

	/**
	 * Returns the durability
	 * 
	 * @return the durability
	 */
	public int getDurability()
	{
		return this.durability;
	}

	/**
	 * Sets the durability
	 * 
	 * @param durability
	 *            the durability to set
	 */
	public void setDurability(int durability)
	{
		this.durability = durability;
	}

	/**
	 * Called when block is hit. Decrements durability and removes block if
	 * destroyed
	 */
	public void hit()
	{
		this.durability--;
		if(this.durability == 0) this.remove(true);
	}

	/**
	 * Destroys the block and any powerup that might be in the block
	 */
	public void destroy()
	{
		this.durability = 0;
		this.remove(true);
		if(this.powerup != null) this.powerup.remove(true);
	}

	/**
	 * Returns the powerup
	 * 
	 * @return the powerup
	 */
	public EntityPowerup getPowerup()
	{
		return this.powerup;
	}

	/**
	 * Sets the powerup
	 * 
	 * @param powerup
	 *            the powerup to set
	 */
	public void setPowerup(EntityPowerup powerup)
	{
		this.powerup = powerup;
	}

	/**
	 * Returns the initial durability
	 * 
	 * @return the initial durability
	 */
	public int getInitialDurability()
	{
		return initialDurability;
	}
}
