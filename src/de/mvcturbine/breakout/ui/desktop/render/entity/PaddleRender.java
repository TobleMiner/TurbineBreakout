package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class PaddleRender extends EntityRender
{

	private static Color COLOR_DEFAULT = Color.BLACK;
	private static Color COLOR_ARMED = Color.RED;
	private static Color COLOR_STICKY = Color.YELLOW;

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityPaddle paddle = (EntityPaddle) e;
		Graphics2D gfx = (Graphics2D) img.getGraphics();
		gfx.setColor(paddle.isArmed() ? COLOR_ARMED : COLOR_DEFAULT);
		int height = (int) (paddle.getSize().height * scale.height);
		int width = (int) (paddle.getSize().width * scale.width);
		int posX = (int) (paddle.getLocation().getX() * scale.getX());
		int posY = img.getHeight() -
				(int) (paddle.getLocation().getY() * scale.getY() + height);
		gfx.fillRect(posX, posY, width, height);
		if(paddle.isSticky())
		{
			gfx.setColor(COLOR_STICKY);
			gfx.drawLine(posX, posY, posX + width, posY);
		}
	}

}
