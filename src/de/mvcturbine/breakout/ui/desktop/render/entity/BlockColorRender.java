package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders a block in a solid color. Fallback render if textures for texture
 * render can't be loaded
 * 
 * @author tsys
 *
 */
public class BlockColorRender extends EntityRender
{
	/** Colors for different levels of destroyedness */
	private static Color[] colors = { Color.WHITE, Color.BLUE, Color.GREEN, Color.GRAY,
			Color.DARK_GRAY };

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityBlock block = (EntityBlock) e;
		Color c = colors[block.getDurability()];
		Graphics2D gfx = (Graphics2D) img.getGraphics();
		gfx.setColor(c);
		double height = block.getSize().height * scale.height;
		gfx.drawRect((int) (block.getLocation().getX() * scale.getX()),
				img.getHeight() -
						(int) (block.getLocation().getY() * scale.getY() + height),
				(int) (block.getSize().width * scale.width), (int) height);
	}
}
