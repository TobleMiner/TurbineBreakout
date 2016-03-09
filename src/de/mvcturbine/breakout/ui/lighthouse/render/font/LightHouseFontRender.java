package de.mvcturbine.breakout.ui.lighthouse.render.font;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class LightHouseFontRender implements LightHouseFont
{
	private static Dimension SIZE = new Dimension(10, 12);

	private HashMap<Character, BufferedImage> charset = new HashMap<>();

	public LightHouseFontRender()
	{
		prerenderFont();
	}

	private void prerenderFont()
	{
		Font font = new Font("Monospaced", Font.PLAIN, SIZE.height);
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!_-/ ";
		for(int i = 0; i < chars.length(); i++)
		{
			String s = chars.substring(i, i + 1);
			BufferedImage img = new BufferedImage(SIZE.width, SIZE.height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D gfx = (Graphics2D) img.getGraphics();
			gfx.setBackground(Color.BLACK);
			gfx.clearRect(0, 0, SIZE.width, SIZE.height);
			gfx.setColor(Color.WHITE);
			gfx.setFont(font);
			gfx.drawString(s, 0,
					font.getLineMetrics(s,
							new FontRenderContext(gfx.getTransform(), true, false))
							.getAscent());
			this.charset.put(s.charAt(0), img);
		}
	}

	@Override
	public Dimension getFontSize()
	{
		return SIZE;
	}

	@Override
	public BufferedImage getChar(char c)
	{
		return this.charset.get(c);
	}

}
