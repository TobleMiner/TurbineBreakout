package de.mvcturbine.breakout.ui.lighthouse.render.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class BallRender extends EntityRender
{
	private static Color COLOR_DEFAULT = Color.WHITE;
	private static Color COLOR_BREAKTHROUGH = Color.ORANGE;

	@Override
	protected void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityBall ball = (EntityBall) e;
		Color c = ball.isBreakthrough() ? COLOR_BREAKTHROUGH : COLOR_DEFAULT;
		drawEntity(ball, img, scale, c);
	}

}
