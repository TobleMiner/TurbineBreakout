package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders Laz0r beams
 * 
 * @author tsys
 *
 */
public class Laz0rBeamRender extends EntityRender
{
	private static Color LAZ0R_COLOR = Color.RED;

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		Graphics2D gfx = (Graphics2D) img.getGraphics();
		gfx.setColor(LAZ0R_COLOR);
		double height = e.getSize().height * scale.height;
		gfx.fillRect((int) (e.getLocation().getX() * scale.getX()),
				img.getHeight() - (int) (e.getLocation().getY() * scale.getY() + height),
				(int) (e.getSize().width * scale.width), (int) height);
	}

}
