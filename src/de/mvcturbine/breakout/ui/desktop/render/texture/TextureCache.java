package de.mvcturbine.breakout.ui.desktop.render.texture;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureCache
{
	private HashMap<String, Image> cache = new HashMap<>();

	private BufferedImage[] textures;

	public TextureCache(int size)
	{
		this.textures = new BufferedImage[size];
	}

	public Image getTexture(Dimension size, int index)
	{
		String id = String.format("%d=%d:%d", index, size.width, size.height);
		Image texture = this.cache.get(id);
		if(texture == null)
		{
			texture = this.textures[index].getScaledInstance(size.width, size.height,
					BufferedImage.SCALE_FAST);
			this.cache.put(id, texture);
		}
		return texture;
	}

	public void loadImageFromResource(String uri, int index) throws IOException
	{
		InputStream stream = this.getClass().getResourceAsStream(uri);
		this.textures[index] = ImageIO.read(stream);
	}

	/**
	 * @param texture
	 *            the texture to set
	 * @param index
	 *            index of the texture
	 */
	public void setImage(BufferedImage texture, int index)
	{
		this.textures[index] = texture;
	}
}
