package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.breakout.world.physics.BlockPhysics;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class EntityBlock extends Entity {

	public EntityBlock(World w) {

		super(w);
		this.physics = new BlockPhysics(this);
		this.size = new Size2D(2d, 1d);
	}
}
