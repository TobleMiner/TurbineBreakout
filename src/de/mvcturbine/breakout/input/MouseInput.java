package de.mvcturbine.breakout.input;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Loc2D;

public class MouseInput implements MouseMotionListener, MouseListener
{

	private WorldBreakout world;
	private EntityPaddle paddle;
	private double xViewFactor;

	public MouseInput(WorldBreakout world, Dimension viewSize)
	{
		this.world = world;
		this.paddle = world.getPaddle();
		xViewFactor = world.getSize().getWidth() / viewSize.getWidth();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Loc2D newLoc = new Loc2D(e.getX() * xViewFactor - paddle.getSize().getWidth() / 2,
				paddle.getLocation().getY());
		if(this.paddle.canMoveTo(newLoc)) this.paddle.setLocation(newLoc);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(this.world.getPaddle().isArmed()) this.world.getPaddle().shoot();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

}
