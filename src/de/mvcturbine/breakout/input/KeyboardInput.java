package de.mvcturbine.breakout.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.util.geom.Loc2D;

public class KeyboardInput implements KeyListener
{

	private WorldBreakout w;
	private EntityPaddle paddle;

	public KeyboardInput(WorldBreakout world)
	{
		this.w = world;
		this.paddle = (EntityPaddle) w.getPaddle();
	}

	@Override
	public void keyPressed(KeyEvent event)
	{

		switch(event.getKeyCode())
		{
			case KeyEvent.VK_A:
				Loc2D newLoc = paddle.getLocation().clone().add(new Loc2D(-0.5, 0));
				if(this.paddle.canMoveTo(newLoc)) this.paddle.setLocation(newLoc);
				break;
			case KeyEvent.VK_D:
				newLoc = paddle.getLocation().clone().add(new Loc2D(0.5, 0));
				if(this.paddle.canMoveTo(newLoc)) this.paddle.setLocation(newLoc);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

}
