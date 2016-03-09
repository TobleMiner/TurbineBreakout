package de.mvcturbine.breakout.world;

import java.awt.Dimension;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
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
		this.ball.setLocation(new Loc2D(size.width / 2, 2.5));
		this.entityRegistry.add(this.ball);
		this.addObserver(this.ball);
		this.paddle = new EntityPaddle(this);
		this.paddle
				.setLocation(new Loc2D(size.width / 2 - paddle.getSize().width / 2, 2));
		this.entityRegistry.add(this.paddle);
		this.addObserver(this.paddle);
		double block_width = 2d;
		double block_height = 1d;
		double block_spacing = 0.45d;
		int num_blocks = (int) (size.width / (block_spacing + block_width));
		int num_rows = 5;
		for(int y = 0; y < num_rows; y++)
		{
			for(int x = 0; x < num_blocks; x++)
			{
				EntityBlock block = new EntityBlock(this);
				block.setLocation(new Loc2D(block_spacing * (x + 1) + block_width * x,
						size.height - ((y + 1) * block_height + y * block_spacing)));
				this.addObserver(block);
				this.entityRegistry.add(block);
			}
		}
	}

	@Override
	public void tick()
	{
		super.tick();
		boolean finish = true;
		for(Entity e : this.entityRegistry)
		{
			if(e instanceof EntityBlock)
			{
				finish = false;
			}
		}
		// if(finish) this.getGame().stop();
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
