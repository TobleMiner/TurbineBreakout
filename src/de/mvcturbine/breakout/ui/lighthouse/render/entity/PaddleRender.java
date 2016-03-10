package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class PaddleRender extends EntityRender
{
	private static Color COLOR_ARMED = Color.RED;
	private static Color COLOR_DEFAULT = Color.GREEN;

	@Override
	protected void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityPaddle paddle = (EntityPaddle) e;
		Color c = paddle.isArmed() ? COLOR_ARMED : COLOR_DEFAULT;
		drawEntity(e, img, scale, c);
	}
}
