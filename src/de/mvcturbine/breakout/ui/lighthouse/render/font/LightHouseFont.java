package de.mvcturbine.breakout.ui.lighthouse.render.font;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public interface LightHouseFont
{
	public Dimension getFontSize();

	public BufferedImage getChar(char c);
}
