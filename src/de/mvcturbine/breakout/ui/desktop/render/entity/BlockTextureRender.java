package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class BlockTextureRender extends EntityTextureRender
{
	public BlockTextureRender() throws IOException
	{
		super();
	}

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityBlock block = (EntityBlock) e;
		Graphics2D gfx = (Graphics2D) img.getGraphics();

		double width = block.getSize().getWidth() * scale.getX();
		double height = block.getSize().getHeight() * scale.getY();

		Image texture = this.cache.getTexture(new Dimension((int) width, (int) height),
				block.getDurability());

		gfx.drawImage(texture, (int) (block.getLocation().getX() * scale.getX()), img
				.getHeight() -
				(int) ((block.getSize().getHeight() + block.getLocation().getY()) *
						scale.getY()),
				null);

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
