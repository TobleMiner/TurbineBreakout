package de.mvcturbine.breakout.ui.desktop.render.texture;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Class to hold textures in different sizes;
 * 
 * @author tsys
 *
 */
public class TextureCache
{
	/** The actual cache */
	private HashMap<String, Image> cache = new HashMap<>();

	/** The base textures */
	private BufferedImage[] textures;

	/** Initializes a new cache for {@code size} different textures */
	public TextureCache(int size)
	{
		this.textures = new BufferedImage[size];
	}

	/**
	 * Gets a texture from cache or rescales a texture and stores it if
	 * necessary.
	 * 
	 * @param size
	 *            The size of the texture
	 * @param index
	 *            The index of the base texture
	 * @return The texture
	 */
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

	/**
	 * Loads a base texture into this cache
	 * 
	 * @param uri
	 *            The resource uri of the texture
	 * @param index
	 *            The index to store the texture at
	 * @throws IOException
	 *             if an error occurred loading the texture
	 */
	public void loadImageFromResource(String uri, int index) throws IOException
	{
		InputStream stream = this.getClass().getResourceAsStream(uri);
		this.textures[index] = ImageIO.read(stream);
	}

	/**
	 * Sets a base texture into this cache
	 * 
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
