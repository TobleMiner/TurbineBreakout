package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class BallRender extends EntityRender
{
	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		renderBall((EntityBall) e, img, scale);
	}

	private void renderBall(EntityBall ball, BufferedImage img, Size2D scale)
	{

	}
}
