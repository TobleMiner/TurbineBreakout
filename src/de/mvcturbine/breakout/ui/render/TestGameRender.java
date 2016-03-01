package de.mvcturbine.breakout.ui.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Loc2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class TestGameRender extends JPanel implements Observer
{
	public final World world;

	public TestGameRender(World world)
	{
		this.world = world;
		this.world.addObserver(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(this.render(this.getWidth(), this.getHeight()), 0, 0, null);
	}

	private void renderEntity(Entity e, Color c, BufferedImage img, double scaleX,
			double scaleY)
	{
		Loc2D loc = e.getLocation();
		for(int y = 0; y < e.getSize().height * scaleY; y++)
		{
			for(int x = 0; x < e.getSize().width * scaleX; x++)
			{
				try
				{
					img.setRGB((int) (loc.x * scaleX + x), (int) (loc.y * scaleY + y),
							c.getRGB());
				}
				catch(Exception ex)
				{
					// System.out.println(new Loc2D((int) (loc.x * scaleX + x),
					// (int) (loc.y * scaleY + y)).toString());
				}
			}
		}
	}

	public Image render(int width, int height)
	{
		Dimension size = this.world.getSize();
		double scaleX = (double) width / size.width;
		double scaleY = (double) height / size.height;
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.clearRect(0, 0, width, height);
		for(Entity ent : this.world.getAllEntities())
		{
			if(ent instanceof EntityBall)
			{
				renderEntity(ent, Color.ORANGE, img, scaleX, scaleY);
			}
			else if(ent instanceof EntityPaddle)
			{
				renderEntity(ent, Color.BLUE, img, scaleX, scaleY);
			}
		}
		return img;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		this.repaint();
		this.invalidate();
	}
}
