package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import de.mvcturbine.breakout.ui.desktop.render.texture.TextureCache;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Base class for textured entity renders
 * 
 * @author tsys
 *
 */
public abstract class EntityTextureRender extends EntityRender
{
	/** The texture cache */
	protected TextureCache cache;

	/**
	 * Constructs a new texture render
	 * 
	 * @throws IOException
	 *             if texture loading fails
	 */
	public EntityTextureRender() throws IOException
	{
		this.cache = new TextureCache(this.getTextureCount());
		this.initCache();
	}

	/**
	 * Draws the texture at {@code textureIndex} form the {@code cache} onto
	 * {@code img} at the position of {@code e} scaled by {@code scale}
	 * 
	 * @param e
	 *            The entity
	 * @param img
	 *            The target image
	 * @param scale
	 *            The scaling factors
	 * @param textureIndex
	 *            Index of texture in cache
	 */
	protected void drawTexture(Entity e, BufferedImage img, Size2D scale,
			int textureIndex)
	{
		Graphics2D gfx = (Graphics2D) img.getGraphics();

		double width = e.getSize().getWidth() * scale.getX();
		double height = e.getSize().getHeight() * scale.getY();

		Image texture = this.cache.getTexture(new Dimension((int) width, (int) height),
				textureIndex);

		gfx.drawImage(texture, (int) (e.getLocation().getX() * scale.getX()), img
				.getHeight() -
				(int) ((e.getSize().getHeight() + e.getLocation().getY()) * scale.getY()),
				null);
	}

	/**
	 * Returns number of textures for the cache to hold
	 * 
	 * @return number of textures for the cache to hold
	 */
	protected abstract int getTextureCount();

	/**
	 * Loads all textures to the cache
	 * 
	 * @throws IOException
	 *             if texture loading fails
	 */
	protected abstract void initCache() throws IOException;
}
