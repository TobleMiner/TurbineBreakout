package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public abstract class EntityRender extends de.mvcturbine.ui.render.entity.EntityRender
{
	protected void drawEntity(Entity e, BufferedImage img, Size2D scale, Color c)
	{
		Loc2D pos = e.getLocation();
		Size2D size = e.getSize();
		for(double y = 0; y < size.height; y += 1 / scale.height)
		{
			for(double x = 0; x < size.width; x += 1 / scale.width)
			{
				int posX = (int) Math.round((pos.getX() + x) * scale.getX());
				int posY = (int) Math.round((pos.getY() + y) * scale.getY());
				if(posX >= 0 && posX < img.getWidth() && posY >= 0 &&
						posY < img.getHeight())
					img.setRGB(posX, posY, c.getRGB());
			}
		}

	}
}
