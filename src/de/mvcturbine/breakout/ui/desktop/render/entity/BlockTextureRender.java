package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders a textured block
 * 
 * @author tsys
 *
 */
public class BlockTextureRender extends EntityTextureRender
{
	/**
	 * Constructs the block texture render
	 * 
	 * @throws IOException
	 *             if any textures can't be loaded
	 */
	public BlockTextureRender() throws IOException
	{
		super();
	}

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityBlock block = (EntityBlock) e;
		drawTexture(e, img, scale, block.getInitialDurability());
	}

	@Override
	protected int getTextureCount()
	{
		return EntityBlock.MAX_DURABILITY + 1;

	}

	@Override
	protected void initCache() throws IOException
	{
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/blockYellow.png", 1);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/blockGreen.png", 2);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/blockRed.png", 3);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/blockBlue.png", 4);
	}
}
