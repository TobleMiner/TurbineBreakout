package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityGameAction extends Entity
{

	protected EntityGameAction(World w)
	{
		super(w);
	}

	@Override
	public Size2D getSize()
	{
		// TODO Auto-generated method stub
		return new Size2D(this.world.getSize().width, 1);
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

}
