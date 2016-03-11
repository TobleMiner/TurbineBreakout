package de.mvcturbine.breakout.ui.lighthouse.render.font;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * Interface for fonts displayable on the lighthouse
 * 
 * @author tsys
 *
 */
public interface LightHouseFont
{
	/**
	 * Get the size of the font
	 * 
	 * @return the size of the font
	 */
	public Dimension getFontSize();

	/**
	 * The prerendered representation of {@code c}
	 * 
	 * @param c
	 *            the char to get
	 * @return a prerendered representation of {@code c}
	 */
	public BufferedImage getChar(char c);
}
