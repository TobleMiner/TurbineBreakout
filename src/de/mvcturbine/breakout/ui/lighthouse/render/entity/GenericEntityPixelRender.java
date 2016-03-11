package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Generic render for the lighthouse. Draws entities by their locaton and size
 * 
 * @author tsys
 *
 */
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
		this.drawEntity(e, img, scale, c);
	}

	private Color generateRandomColor()
	{
		return new Color(this.rand.nextInt((int) Math.pow(2, 24)));
	}
}
