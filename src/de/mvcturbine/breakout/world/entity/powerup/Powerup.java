package de.mvcturbine.breakout.world.entity.powerup;

import java.awt.Color;

public enum Powerup
{
	PADDLE_LARGER(Color.BLUE),
	PADDLE_SMALLER(Color.ORANGE),
	PADDLE_STICKY(Color.GREEN),
	PADDLE_LAZ0R(Color.RED),
	BALL_MULTI(Color.CYAN),
	BALL_EXPLOSIVE(Color.YELLOW),
	BALL_BREAKTHROUGH(Color.GRAY);

	private Color color;

	private Powerup(Color c)
	{
		this.color = c;
	}

	/**
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}
}
