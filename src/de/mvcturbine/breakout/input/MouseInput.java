package de.mvcturbine.breakout.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import de.mvcturbine.breakout.ui.desktop.DesktopWorldView;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Loc2D;

public class MouseInput implements MouseMotionListener, MouseListener
{
	/** The world this input listener modifies */
	private WorldBreakout world;

	/** The view the mouse moves in */
	private DesktopWorldView view;

	/**
	 * Creates a new input listener on mouse input for the specified
	 * {@link WorldBreakout} and {@link DesktopWorldView}
	 * 
	 * @param world
	 * @param view
	 */
	public MouseInput(WorldBreakout world, DesktopWorldView view)
	{
		this.world = world;
		this.view = view;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		EntityPaddle paddle = this.world.getPaddle();
		double xfactor = this.world.getSize().getWidth() / this.view.getWidth();
		Loc2D newLoc = new Loc2D(e.getX() * xfactor - paddle.getSize().getWidth() / 2,
				paddle.getLocation().getY());
		if(paddle.canMoveTo(newLoc)) paddle.setLocation(newLoc);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(this.world.getPaddle().isArmed()) this.world.getPaddle().shoot();
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

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

}
