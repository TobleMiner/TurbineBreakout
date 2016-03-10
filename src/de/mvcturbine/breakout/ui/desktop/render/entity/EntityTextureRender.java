package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.io.IOException;

import de.mvcturbine.breakout.ui.desktop.render.texture.TextureCache;
import de.mvcturbine.ui.render.entity.EntityRender;

public abstract class EntityTextureRender extends EntityRender
{
	protected TextureCache cache;

	public EntityTextureRender() throws IOException
	{
		this.cache = new TextureCache(this.getTextureCount());
		this.initCache();
	}

	protected abstract int getTextureCount();

	protected abstract void initCache() throws IOException;
}
