package de.mvcturbine.breakout.world.entity.fx;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingEntity;

public class EntityScoreParticle extends MovingEntity
{
	private final Size2D size = new Size2D(3, 1);

	private final double diff;

	private final long creationTick;

	public EntityScoreParticle(WorldBreakout w, double diff)
	{
		super(w);
		this.diff = diff;
		Breakout game = (Breakout) w.getGame();
		creationTick = game.getTicks();
	}

	@Override
	public Size2D getSize()
	{
		return size;
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

	@Override
	public WorldBreakout getWorld()
	{
		return (WorldBreakout) super.getWorld();
	}

	/**
	 * @return the diff
	 */
	public double getDiff()
	{
		return diff;
	}

	@Override
	protected void worldUpdate(World w)
	{
		super.worldUpdate(w);
		Breakout game = (Breakout) w.getGame();
		if(game.getTicks() > creationTick + game.getTPS() * 1.5)
		{
			this.remove(true);
		}
	}

}
