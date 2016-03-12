package de.mvcturbine.breakout.world;

import java.awt.Dimension;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityGameAction;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.fx.EntityScore;
import de.mvcturbine.breakout.world.entity.fx.EntityScoreParticle;
import de.mvcturbine.game.Game;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Vec2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;
import de.mvcturbine.world.entity.MovingEntity;

public class WorldBreakout extends World implements EntityGameAction.ActionCallback
{
	/** Point per block hit */
	private static int POINTS_HIT = 5;
	/** Points per destroyed block */
	private static int POINTS_BREAK = 100;

	/** The ball entity */
	private EntityBall ball;
	/** The paddle entity */
	private EntityPaddle paddle;
	/** The score entity */
	private EntityScore entityScore;

	/** The score multiplier */
	private double multiplier;
	/** The score */
	private double score;

	private GameCallback callback;

	/**
	 * Constructs a new breakout world
	 * 
	 * @param game
	 *            The game (you lost)
	 * @param size
	 *            The size of the world
	 */
	public WorldBreakout(Game game, Dimension size)
	{
		super(game, size);
	}

	@Override
	public void tick()
	{
		super.tick();
		if(this.callback != null)
		{
			boolean finish = true;
			for(Entity e : this.entityRegistry)
			{
				if(e instanceof EntityBlock)
				{
					finish = false;
				}
			}
			if(finish) this.callback.onWin(this);
		}
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
		if(who instanceof EntityBall)
		{
			if(this.callback != null) this.callback.onLoose(this);
		}
	}

	/**
	 * Resets the score multiplier
	 */
	public void combobreaker()
	{
		this.multiplier = 1;
	}

	/**
	 * Sets the score and spawns a visual entity showing the score change
	 * 
	 * @param score
	 */
	protected void setScore(double score)
	{
		Double diff = score - this.score;
		Loc2D particlePos = this.entityScore.getLocation().clone();

		EntityScoreParticle particle = new EntityScoreParticle(this, diff);
		particle.setLocation(particlePos);
		particle.setVelocity(new Vec2D(0, 2));
		addEntity(particle);

		this.score = score;
	}

	/**
	 * Scores a block hit
	 */
	public void scoreHit()
	{
		setScore(this.score + (POINTS_HIT * this.multiplier));
	}

	/**
	 * Scores a block break
	 */
	public void scoreBreak()
	{
		setScore(this.score + (POINTS_BREAK * this.multiplier));
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
	 * @return the score
	 */
	public double getScore()
	{
		return score;
	}

	public GameCallback getCallback()
	{
		return callback;
	}

	public void setCallback(GameCallback callback)
	{
		this.callback = callback;
	}

	/**
	 * @return the entityScore
	 */
	public EntityScore getEntityScore()
	{
		return entityScore;
	}

	/**
	 * @param entityScore
	 *            the entityScore to set
	 */
	public void setEntityScore(EntityScore entityScore)
	{
		this.entityScore = entityScore;
	}

	public interface GameCallback
	{
		public void onWin(WorldBreakout world);

		public void onLoose(WorldBreakout world);
	}

	@Override
	public void resetWorld()
	{
		super.resetWorld();
		this.score = 0;
		this.entityScore = null;
		this.ball = null;
		this.paddle = null;
		this.multiplier = 1;
	}

	public void removeBall()
	{
		this.ball.remove(true);
	}
}
