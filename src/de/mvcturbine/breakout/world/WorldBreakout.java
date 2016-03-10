package de.mvcturbine.breakout.world;

import java.awt.Dimension;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityGameAction;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.fx.EntityScore;
import de.mvcturbine.breakout.world.level.LevelGenerator;
import de.mvcturbine.game.Game;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.entity.MovingEntity;

public class WorldBreakout extends World implements EntityGameAction.ActionCallback
{
	private static int POINTS_HIT = 5;
	private static int POINTS_BREAK = 100;

	private EntityBall ball;
	private EntityPaddle paddle;

	private double powerUpFallTime = 0.2d;

	private double multiplier;
	private double score;

	public WorldBreakout(Game game, Dimension size)
	{
		super(game, size);
		LevelGenerator gen = new LevelGenerator(this);
		gen.populateWorld();

		EntityScore score = new EntityScore(this);
		score.setLocation(new Loc2D(0, 1));
		addEntity(score);
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
	public EntityPaddle getPaddle()
	{
		return paddle;
	}

	@Override
	public void actionEntityHit(EntityGameAction entity, Entity who)
	{
		if(who instanceof MovingEntity) who.remove(true);
	}

	public void combobreaker()
	{
		this.multiplier = 1;
	}

	public void scoreHit()
	{
		this.score += POINTS_HIT * this.multiplier;
	}

	public void scoreBreak()
	{
		this.score += POINTS_BREAK * this.multiplier;
	}

	/**
	 * @param ball
	 *            the ball to set
	 */
	public void setBall(EntityBall ball)
	{
		this.ball = ball;
	}

	/**
	 * @param paddle
	 *            the paddle to set
	 */
	public void setPaddle(EntityPaddle paddle)
	{
		this.paddle = paddle;
	}

	/**
	 * @return the powerUpFallTime
	 */
	public double getPowerUpFallTime()
	{
		return powerUpFallTime;
	}

	/**
	 * @param powerUpFallTime
	 *            the powerUpFallTime to set
	 */
	public void setPowerUpFallTime(double powerUpFallTime)
	{
		this.powerUpFallTime = powerUpFallTime;
	}

	/**
	 * @return the score
	 */
	public double getScore()
	{
		return score;
	}
}
