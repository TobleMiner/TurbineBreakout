package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders texture overlays over blocks to indicate their state of destruction
 * 
 * @author tsys
 *
 */
public class BlockTextureOverlayRender extends EntityTextureRender
{
	/** The underlying texture render */
	private final BlockTextureRender textureRender;

	/**
	 * Constructs the overly render
	 * 
	 * @throws IOException
	 *             if any of the textures can't be found
	 */
	public BlockTextureOverlayRender() throws IOException
	{
		super();
		textureRender = new BlockTextureRender();
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
				"/de/mvcturbine/breakout/resources/graphics/textures/overlay/minimalwear.png",
				1);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/overlay/fieldtested.png",
				2);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/overlay/wellworn.png",
				3);
		this.cache.loadImageFromResource(
				"/de/mvcturbine/breakout/resources/graphics/textures/overlay/gabonscarred.png",
				4);

	}

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		textureRender.renderEntity(e, img, scale);

		EntityBlock block = (EntityBlock) e;
		if(block.getDurability() != 0 &&
				block.getDurability() != block.getInitialDurability())
		{
			drawTexture(e, img, scale,
					(EntityBlock.MAX_DURABILITY - block.getInitialDurability() + 1) +
							(block.getInitialDurability() - block.getDurability()));
		}
	}

}
