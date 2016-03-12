package de.mvcturbine.breakout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;

public class Main extends JFrame implements Runnable
{
	private Breakout breakout;

	@Override
	public void run()
	{
		this.breakout = new Breakout(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Breakout");
		this.breakout.init();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}
}
