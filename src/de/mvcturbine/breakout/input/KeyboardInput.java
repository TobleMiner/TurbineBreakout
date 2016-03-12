package de.mvcturbine.breakout.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Loc2D;

/**
 * A keyboard input listener to listen to user commands
 * 
 * @author tsys
 *
 */
public class KeyboardInput implements KeyListener
{
	/** The world this listener does modify */
	private WorldBreakout w;

	/**
	 * Constructs a new input class for the given {@link WorldBreakout}
	 * 
	 * @param world
	 */
	public KeyboardInput(WorldBreakout world)
	{
		this.w = world;
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		EntityPaddle paddle = this.w.getPaddle();
		switch(event.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				// Move paddle left
				Loc2D newLoc = paddle.getLocation().clone().add(new Loc2D(-0.5, 0));
				if(paddle.canMoveTo(newLoc)) paddle.setLocation(newLoc);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				// Move paddle right
				newLoc = paddle.getLocation().clone().add(new Loc2D(0.5, 0));
				if(paddle.canMoveTo(newLoc)) paddle.setLocation(newLoc);
				break;
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				// Shoot
				if(paddle.isArmed()) paddle.shoot();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

}
