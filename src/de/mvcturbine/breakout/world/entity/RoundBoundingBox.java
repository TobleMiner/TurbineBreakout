package de.mvcturbine.breakout.world.entity;

import de.mvcturbine.util.geom.EntityBB.Moving;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.entity.MovingEntity;

public class RoundBoundingBox extends Moving
{

	private static int CIRCLE_CORNERS = 100;

	public RoundBoundingBox(MovingEntity e)
	{
		super(e);
	}

	@Override
	public Loc2D[] getCorners()
	{

		Loc2D[] corners = new Loc2D[CIRCLE_CORNERS];
		Loc2D middle = entity.getLocation().clone()
				.add(new Vec2D(entity.getSize()).divide(2));
		Vec2D vec = new Vec2D(this.entity.getSize().getWidth(), 0).divide(2);

		assert entity.getSize().getHeight() == entity.getSize().getWidth();

		for(int i = 0; i < CIRCLE_CORNERS; i++)
		{
			vec.rotate((2 * Math.PI) / CIRCLE_CORNERS);
			corners[i] = middle.clone().add(vec);
		}
		return corners;
	}
}
