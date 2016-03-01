package de.mvcturbine.breakout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.ui.render.TestGameRender;
import de.mvcturbine.breakout.world.WorldBreakout;

public class Main extends JFrame implements Runnable
{
	@Override
	public void run()
	{
		Dimension worldSize = new Dimension(20, 20);
		Breakout b = new Breakout();
		WorldBreakout world = new WorldBreakout(b, worldSize);
		TestGameRender tgr = new TestGameRender(world);
		this.getContentPane().add(tgr);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Breakout");
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(200, 200));
		b.init();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}
}
