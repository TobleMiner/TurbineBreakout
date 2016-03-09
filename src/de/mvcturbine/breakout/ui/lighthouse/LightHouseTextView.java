package de.mvcturbine.breakout.ui.lighthouse;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Observable;

import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.breakout.ui.lighthouse.render.font.LightHouseFont;
import de.mvcturbine.game.Game;

public class LightHouseTextView extends LightHouseView
{
	private LightHouseFont font;
	private double speed;
	private Game game;
	private double scroll = 0;
	private BufferedImage prerender;

	public LightHouseTextView(double scrollSpeed, String text, LightHouseFont font,
			Game game, LhNetwork lighthouse)
	{
		super(lighthouse);
		this.speed = scrollSpeed;
		this.font = font;
		this.game = game;
		this.prerender(text);
	}

	protected void prerender(String str)
	{
		Dimension fontSize = this.font.getFontSize();
		int width = str.length() * fontSize.width;
		BufferedImage image = new BufferedImage(width,
				(int) this.lighthouse.getSize().height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gfx = (Graphics2D) image.getGraphics();
		char[] text = str.toUpperCase().toCharArray();
		for(int i = 0; i < text.length; i++)
		{
			gfx.drawImage(this.font.getChar(text[i]), i * fontSize.width, 0, null);
		}
		AffineTransform flip = AffineTransform.getScaleInstance(1, -1);
		flip.translate(0, -image.getHeight());
		AffineTransformOp op = new AffineTransformOp(flip,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		this.prerender = op.filter(image, null);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(this.prerender == null || !this.lighthouse.connected()) return;
		Dimension fontSize = this.font.getFontSize();
		this.scroll += this.speed * this.font.getFontSize().getWidth() /
				this.game.getTPS();
		BufferedImage part = this.prerender.getSubimage((int) this.scroll, 0,
				fontSize.width, fontSize.height);
		BufferedImage lhImg = new BufferedImage((int) this.lighthouse.getSize().width,
				(int) this.lighthouse.getSize().height, BufferedImage.TYPE_3BYTE_BGR);
		lhImg.getGraphics().drawImage(
				part.getScaledInstance((int) this.lighthouse.getSize().width,
						(int) this.lighthouse.getSize().height, Image.SCALE_FAST),
				0, 0, null);
		this.lighthouse.setFrame(lhImg);
	}
}
