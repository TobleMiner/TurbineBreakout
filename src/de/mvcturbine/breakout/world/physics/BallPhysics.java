package de.mvcturbine.breakout.world.physics;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.BoundingBox;
import de.mvcturbine.util.geom.Direction;
import de.mvcturbine.util.geom.EntityBB;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.physics.PhysicsModel;

public class BallPhysics extends PhysicsModel {
	public BallPhysics(EntityBall ball) {
		super(ball);
	}

	@Override
	public void apply() {
		EntityBall ball = (EntityBall) this.entity;
		WorldBreakout w = (WorldBreakout) ball.getWorld();
		EntityBB.Moving bbb = ball.getBounds();

		for (Entity ent : w.getAllEntities()) {
			if (ent instanceof EntityPaddle || ent instanceof EntityBlock) {

				BoundingBox entbb = ent.getBounds();

				if (bbb.intersects(entbb)) {
					double angle = bbb.getCollisionAngle(entbb);
					if (!Double.isNaN(angle)) {
						ball.setVelocity(ball.getVelocity().setAngle(angle));
					}

					// int i = bbb.numberOfIntersectingCorners(pbb);
					// System.out.format("Intersections: %d\n", i);
					// Direction dir = bbb.getInnerCollidingFace(pbb);
					// Vec2D vec = ball.getVelocity();
					// if(i == 1)
					// {
					// vec.multiply(-1);
					// }
					// else
					// {
					// switch(dir)
					// {
					// case NORTH:
					// case SOUTH:
					// vec = new Vec2D(vec.x, -vec.y);
					// break;
					// case EAST:
					// case WEST:
					// vec = new Vec2D(-vec.x, vec.y);
					// break;
					// default:
					// break;
					// }
					// }
					// ball.setVelocity(vec);
				}
			}
			else if (!ball.getBounds().isInside(w.getBounds())) {
				Direction dir = ball.getBounds().getInnerCollidingEdge(w.getBounds());
				Vec2D vec = ball.getVelocity();
				switch (dir) {
				case NORTH:
				case SOUTH:
					vec = new Vec2D(vec.x, -vec.y);
					break;
				case EAST:
				case WEST:
					vec = new Vec2D(-vec.x, vec.y);
					break;
				default:
					break;
				}
				ball.setVelocity(vec);
			}
		}
	}
}
