package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class GenericEntityPixelRender extends EntityRender
{
	private static boolean EPILEPSY = false;

	private HashMap<Entity, Color> colorbucket = new HashMap<>();
	private Random rand = new Random();

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		Color c = this.generateRandomColor();
		if(this.colorbucket.containsKey(e) && !EPILEPSY) c = this.colorbucket.get(e);
		this.colorbucket.put(e, c);
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

	private Color generateRandomColor()
	{
		return new Color(this.rand.nextInt((int) Math.pow(2, 24)));
	}
}
