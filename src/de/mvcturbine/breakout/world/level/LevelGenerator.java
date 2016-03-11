package de.mvcturbine.breakout.world.level;

import java.awt.Dimension;
import java.util.Random;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityGameAction;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.breakout.world.entity.powerup.Powerup;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.util.geom.Vec2D;

/**
 * Customizeable level generator.
 * 
 * @author tsys
 *
 */
public class LevelGenerator
{
	/** The world to populate */
	private final WorldBreakout world;

	/** The size of blocks */
	private Size2D blockSize = new Size2D(2, 1);
	/** The spacing between blocks */
	private Size2D blockSpacing = new Size2D(0.42, 0.42);
	/** Number of rows */
	private int rows = 4;
	/** Minimum durability of bricks */
	private int durabilityMin = 1;
	/** Maximum durability of bricks */
	private int durabilityMax = EntityBlock.MAX_DURABILITY;
	/** Probability of a brick beeing placed */
	private double brickPlaceProbability = 1.0;
	/** Probability of a brick containing a powerup */
	private double powerupProbability = 0.05;
	/** Offset of paddle from ball pit */
	private Vec2D paddleOffset = new Vec2D(0, 0.5);
	/** Size of the paddle */
	private Size2D paddleSize = new Size2D(3, 0.5);
	/** Offset of ball from paddle */
	private Vec2D ballOffset = new Vec2D(1, 1);
	/** Ball speed relative to world size */
	private Vec2D ballSpeedRel = new Vec2D(0.3, 0.3);

	/**
	 * Constructs a new level generator
	 * 
	 * @param world
	 *            The world
	 */
	public LevelGenerator(WorldBreakout world)
	{
		this.world = world;
	}

	/**
	 * Generates and spawns blocks, powerups, ball pit, paddle and ball
	 */
	public void populateWorld()
	{
		Random rand = this.world.getGame().rand;
		Dimension worldSize = this.world.getSize();
		int columns = (int) ((worldSize.width + this.blockSpacing.width) /
				(this.blockSize.width + this.blockSpacing.width));
		double width = columns * this.blockSize.width +
				(columns - 1) * this.blockSpacing.width;
		double border = (this.world.getSize().width - width) / 2;
		for(int y = 0; y < this.rows; y++)
		{
			for(int x = 0; x < columns; x++)
			{
				if(rand.nextDouble() >= this.brickPlaceProbability) continue;
				int durability = this.durabilityMin +
						rand.nextInt(this.durabilityMax - this.durabilityMin + 1);
				EntityBlock block = new EntityBlock(this.world, durability,
						this.blockSize);
				block.setLocation(new Loc2D(
						border + x * (this.blockSize.width + this.blockSpacing.width),
						worldSize.height - (this.blockSpacing.height +
								this.blockSize.height + y *
										(this.blockSize.height + blockSpacing.height))));
				if(rand.nextDouble() < this.powerupProbability)
				{
					Powerup[] powerups = Powerup.values();
					Powerup pup = powerups[rand.nextInt(powerups.length)];
					EntityPowerup powerup = new EntityPowerup(this.world);
					powerup.setEffect(pup);
					block.setPowerup(powerup);
					Loc2D center = block.getLocation().clone()
							.add(new Vec2D(block.getSize()).divide(2));
					Loc2D loc = center.clone()
							.sub(new Vec2D(powerup.getSize()).divide(2));
					powerup.setLocation(loc);
					this.world.addEntity(powerup);
				}
				this.world.addEntity(block);
			}
		}

		spawnEssentials();
	}

	/**
	 * Generates and spawns ball pit, paddle and ball
	 */
	public void spawnEssentials()
	{
		Dimension worldSize = this.world.getSize();

		EntityGameAction ballPit = new EntityGameAction(world,
				new Size2D(worldSize.width, 1), world);
		ballPit.setLocation(new Loc2D());
		this.world.addEntity(ballPit);

		EntityPaddle paddle = new EntityPaddle(this.world, this.paddleSize);
		paddle.setLocation(new Loc2D(worldSize.width / 2 - this.paddleSize.width / 2,
				ballPit.getSize().height).add(this.paddleOffset));
		this.world.setPaddle(paddle);
		this.world.addEntity(paddle);

		EntityBall ball = new EntityBall(this.world);
		ball.setLocation(paddle.getLocation().clone()
				.add(new Vec2D(paddle.getSize().width / 2, paddle.getSize().height)
						.add(this.ballOffset)));
		ball.setVelocity(
				new Vec2D(this.world.getSize().width, 0).multiply(this.ballSpeedRel.x)
						.add(new Vec2D(0, this.world.getSize().height)
								.multiply(this.ballSpeedRel.y)));
		this.world.setBall(ball);
		this.world.addEntity(ball);
	}

	/**
	 * @return the blockSize
	 */
	public Size2D getBlockSize()
	{
		return blockSize;
	}

	/**
	 * @param blockSize
	 *            the blockSize to set
	 */
	public void setBlockSize(Size2D blockSize)
	{
		this.blockSize = blockSize;
	}

	/**
	 * @return the blockSpacing
	 */
	public Size2D getBlockSpacing()
	{
		return blockSpacing;
	}

	/**
	 * @param blockSpacing
	 *            the blockSpacing to set
	 */
	public void setBlockSpacing(Size2D blockSpacing)
	{
		this.blockSpacing = blockSpacing;
	}

	/**
	 * @return the rows
	 */
	public int getRows()
	{
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows)
	{
		this.rows = rows;
	}

	/**
	 * @return the durabilityMin
	 */
	public int getDurabilityMin()
	{
		return durabilityMin;
	}

	/**
	 * @param durabilityMin
	 *            the durabilityMin to set
	 */
	public void setDurabilityMin(int durabilityMin)
	{
		this.durabilityMin = durabilityMin;
	}

	/**
	 * @return the durabilityMax
	 */
	public int getDurabilityMax()
	{
		return durabilityMax;
	}

	/**
	 * @param durabilityMax
	 *            the durabilityMax to set
	 */
	public void setDurabilityMax(int durabilityMax)
	{
		this.durabilityMax = durabilityMax;
	}

	/**
	 * @return the brickPlaceProbability
	 */
	public double getBrickPlaceProbability()
	{
		return brickPlaceProbability;
	}

	/**
	 * @param brickPlaceProbability
	 *            the brickPlaceProbability to set
	 */
	public void setBrickPlaceProbability(double brickPlaceProbability)
	{
		this.brickPlaceProbability = brickPlaceProbability;
	}

	/**
	 * @return the powerupProbability
	 */
	public double getPowerupProbability()
	{
		return powerupProbability;
	}

	/**
	 * @param powerupProbability
	 *            the powerupProbability to set
	 */
	public void setPowerupProbability(double powerupProbability)
	{
		this.powerupProbability = powerupProbability;
	}

	/**
	 * @return the paddleSize
	 */
	public Size2D getPaddleSize()
	{
		return paddleSize;
	}

	/**
	 * @param paddleSize
	 *            the paddleSize to set
	 */
	public void setPaddleSize(Size2D paddleSize)
	{
		this.paddleSize = paddleSize;
	}

	/**
	 * @return the ballOffset
	 */
	public Vec2D getBallOffset()
	{
		return ballOffset;
	}

	/**
	 * @param ballOffset
	 *            the ballOffset to set
	 */
	public void setBallOffset(Vec2D ballOffset)
	{
		this.ballOffset = ballOffset;
	}

	/**
	 * @return the ballSpeedRel
	 */
	public Vec2D getBallSpeedRel()
	{
		return ballSpeedRel;
	}

	/**
	 * @param ballSpeedRel
	 *            the ballSpeedRel to set
	 */
	public void setBallSpeedRel(Vec2D ballSpeedRel)
	{
		this.ballSpeedRel = ballSpeedRel;
	}

	/**
	 * @return the world
	 */
	public WorldBreakout getWorld()
	{
		return world;
	}

	/**
	 * @return the paddleOffset
	 */
	public Vec2D getPaddleOffset()
	{
		return paddleOffset;
	}

	/**
	 * @param paddleOffset
	 *            the paddleOffset to set
	 */
	public void setPaddleOffset(Vec2D paddleOffset)
	{
		this.paddleOffset = paddleOffset;
	}
}
