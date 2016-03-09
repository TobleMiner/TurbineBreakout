package de.mvcturbine.breakout.world.level;

import java.awt.Dimension;
import java.util.Random;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;

public class LevelGenerator
{
	private final WorldBreakout world;

	private Size2D blockSize = new Size2D(2, 1);
	private Size2D blockSpacing = new Size2D(0.42, 0.42);
	private int rows = 4;
	private int durabilityMin = 1;
	private int durabilityMax = EntityBlock.MAX_DURABILITY;
	private double brickPlaceProbability = 1.0;

	public LevelGenerator(WorldBreakout world)
	{
		this.world = world;
	}

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
				this.world.addEntity(block);
			}
		}
	}
}
