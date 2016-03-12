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
import de.mvcturbine.world.World;

/**
 * A view for the lighthouse displaying scroll text
 * 
 * @author tsys
 *
 */
public class LightHouseTextView extends LightHouseView
{
	/** The font to use */
	private LightHouseFont font;

	/** The scroll speed in chars per second */
	private double speed;

	/** The world this view belongs to */
	private World world;

	/** The current scroll position */
	private double scroll = 0;

	/** The prerendered image of the text to display */
	private BufferedImage prerender;

	private boolean finished = false;

	private AnimationFinishedCallback callback;

	/**
	 * Construct a new scroll text for the lighthouse
	 * 
	 * @param scrollSpeed
	 *            Scroll speed in characters per second
	 * @param text
	 *            The text to display
	 * @param font
	 *            The font to use
	 * @param game
	 *            The game instance
	 * @param lighthouse
	 *            The lighthouse network layer
	 */
	public LightHouseTextView(double scrollSpeed, String text, LightHouseFont font,
			World world, LhNetwork lighthouse)
	{
		super(lighthouse);
		this.speed = scrollSpeed;
		this.font = font;
		this.world = world;
		this.prerender(text);
	}

	/**
	 * Prerender the whole text as an image
	 * 
	 * @param str
	 *            The string to render
	 */
	protected void prerender(String str)
	{
		Dimension fontSize = this.font.getFontSize();
		int width = str.length() * fontSize.width + (int) this.lighthouse.getSize().width;
		BufferedImage image = new BufferedImage(width,
				(int) this.lighthouse.getSize().height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gfx = (Graphics2D) image.getGraphics();
		char[] text = str.toUpperCase().toCharArray();
		for(int i = 0; i < text.length; i++)
		{
			gfx.drawImage(this.font.getChar(text[i]), i * fontSize.width, 0, null);
		}
		// Thanks again to the people who decided putting [0, 0] in the BOTTOM
		// left corner
		AffineTransform flip = AffineTransform.getScaleInstance(1, -1);
		flip.translate(0, -image.getHeight());
		AffineTransformOp op = new AffineTransformOp(flip,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		this.prerender = op.filter(image, null);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(this.prerender == null || !this.lighthouse.connected() || this.finished)
			return;
		Dimension fontSize = this.font.getFontSize();
		this.scroll += this.speed * this.font.getFontSize().getWidth() /
				this.world.getGame().getTPS();
		if(this.scroll >= this.prerender.getWidth() - this.lighthouse.getSize().width)
		{
			this.finished = true;
			if(this.callback != null) this.callback.animationFinished(this);
		}
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

	public AnimationFinishedCallback getCallback()
	{
		return callback;
	}

	public void setCallback(AnimationFinishedCallback callback)
	{
		this.callback = callback;
	}

	public interface AnimationFinishedCallback
	{
		public void animationFinished(LightHouseTextView tv);
	}
}
