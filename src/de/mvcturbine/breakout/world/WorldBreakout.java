package de.mvcturbine.breakout.world;

import java.awt.Dimension;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.game.Game;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class WorldBreakout extends World
{
	private EntityBall ball;
	private EntityPaddle paddle;

	public WorldBreakout(Game game, Dimension size)
	{
		super(game, size);
		this.ball = new EntityBall(this);
		this.ball.setVelocity(new Vec2D(new Size2D(size)).divide(3));
		this.ball.setLocation(new Loc2D(size.width / 2, 3));
		this.entityRegistry.add(this.ball);
		this.addObserver(this.ball);
		this.paddle = new EntityPaddle(this);
		this.paddle
				.setLocation(new Loc2D(size.width / 2 - paddle.getSize().width / 2, 5));
		this.entityRegistry.add(this.paddle);
		this.addObserver(this.paddle);
	}

	/**
	 * @return the ball
	 */
	public EntityBall getBall()
	{
		return ball;
	}

	/**
	 * @return the paddle
	 */
	public Entity getPaddle()
	{
		return paddle;
	}
}
