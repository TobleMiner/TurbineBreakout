package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.physics.BallPhysics;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.MovingeEntity;

public class EntityBall extends MovingeEntity
{

	public EntityBall(World w)
	{
		super(w);
		this.physics = new BallPhysics(this);
	}

}
