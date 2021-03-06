package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.powerup.EntityPowerup;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders powerups
 * 
 * @author tsys
 *
 */
public class PowerupRender extends EntityRender
{
	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		Loc2D[] corners = e.getBounds().getCorners();
		Graphics2D gfx = (Graphics2D) img.getGraphics();
		gfx.setColor(((EntityPowerup) e).getEffect().getColor());
		for(int i = 0; i < corners.length; i++)
		{
			gfx.drawLine((int) (corners[i].getX() * scale.getX()),
					img.getHeight() - (int) (corners[i].getY() * scale.getY()),
					(int) (corners[(i + 1) % corners.length].getX() * scale.getX()),
					img.getHeight() - (int) (corners[(i + 1) % corners.length].getY() *
							scale.getY()));
		}
	}

}
