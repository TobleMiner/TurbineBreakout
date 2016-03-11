package de.mvcturbine.breakout.world.entity.powerup;

import java.awt.Color;

/**
 * All powerup types
 * 
 * @author tsys
 *
 */
public enum Powerup
{
	PADDLE_LARGER(Color.BLUE),
	PADDLE_SMALLER(Color.ORANGE),
	// PADDLE_STICKY(Color.GREEN),
	PADDLE_LAZ0R(Color.RED),
	// BALL_MULTI(Color.CYAN),
	// BALL_EXPLOSIVE(Color.YELLOW),
	BALL_BREAKTHROUGH(Color.GRAY);

	/** The color of this powerup */
	private Color color;

	/**
	 * Constructs a new powerup
	 * 
	 * @param c
	 *            Color of the powerup
	 */
	private Powerup(Color c)
	{
		this.color = c;
	}

	/**
	 * Gets the color of this powerup
	 * 
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}
}
