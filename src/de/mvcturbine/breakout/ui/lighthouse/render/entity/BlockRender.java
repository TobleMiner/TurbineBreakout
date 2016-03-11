package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Block render for the lighthouse
 * 
 * @author tsys
 *
 */
public class BlockRender extends EntityRender
{
	private static Color[] COLORS = { Color.BLACK, Color.BLUE, Color.GREEN, Color.ORANGE,
			Color.RED };

	@Override
	protected void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityBlock block = (EntityBlock) e;
		Color c = COLORS[block.getDurability()];
		this.drawEntity(e, img, scale, c);
	}

}
