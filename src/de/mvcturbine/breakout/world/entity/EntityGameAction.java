package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

/**
 * An entity that triggers a callback when an ingame entity hits it
 * 
 * @author tsys
 *
 */
public class EntityGameAction extends Entity
{
	/** The size */
	private Size2D size;

	/** The callback */
	private ActionCallback callback;

	/**
	 * Constructs a new EntityGameAction in {@code w}
	 * 
	 * @param w
	 *            The world
	 * @param size
	 *            The size
	 * @param callback
	 *            The callback
	 */
	public EntityGameAction(World w, Size2D size, ActionCallback callback)
	{
		super(w);
		this.size = size;
		this.callback = callback;
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
		return false;
	}

	/**
	 * Returns the callback
	 * 
	 * @return the callback
	 */
	public ActionCallback getCallback()
	{
		return callback;
	}

	@Override
	protected void worldUpdate(World w)
	{
		super.worldUpdate(w);
		BoundingBox bb = this.getBounds();
		w.getAllEntities().forEach(e ->
		{
			if(e != this && e.getBounds().intersects(bb))
				this.callback.actionEntityHit(this, e);
		});
	}

	/**
	 * Interface for the action callback
	 * 
	 * @author tsys
	 *
	 */
	public static interface ActionCallback
	{
		public void actionEntityHit(EntityGameAction entity, Entity who);
	}
}
