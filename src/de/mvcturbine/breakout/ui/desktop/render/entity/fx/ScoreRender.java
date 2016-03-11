package de.mvcturbine.breakout.ui.desktop.render.entity.fx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.fx.EntityScore;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders the score
 * 
 * @author tsys
 *
 */
public class ScoreRender extends EntityRender
{
	private final Color scoreColor = Color.BLUE;

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		Graphics2D gfx = (Graphics2D) img.getGraphics();
		EntityScore ent = (EntityScore) e;

		gfx.setColor(scoreColor);
		gfx.setFont(new Font("Monospaced", Font.PLAIN,
				(int) (ent.getSize().getHeight() * scale.getY())));

		gfx.drawString(new Integer((int) ent.getWorld().getScore()).toString(),
				(int) (ent.getLocation().getX() * scale.getX()), (img.getHeight()));
	}

}
