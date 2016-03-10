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

public abstract class EntityTextureRender extends EntityRender
{
	protected TextureCache cache;

	public EntityTextureRender() throws IOException
	{
		this.cache = new TextureCache(this.getTextureCount());
		this.initCache();
	}

	protected void drawTexture(Entity e, BufferedImage img, Size2D scale,
			int textureIndex)
	{
		Graphics2D gfx = (Graphics2D) img.getGraphics();

		double width = e.getSize().getWidth() * scale.getX();
		double height = e.getSize().getHeight() * scale.getY();

		Image texture = this.cache
				.getTexture(new Dimension((int) width, (int) height), textureIndex);

		gfx.drawImage(texture, (int) (e.getLocation().getX() * scale.getX()),
				img.getHeight() -
						(int) ((e.getSize().getHeight() + e.getLocation().getY()) *
								scale.getY()),
				null);
	}

	protected abstract int getTextureCount();

	protected abstract void initCache() throws IOException;
}
