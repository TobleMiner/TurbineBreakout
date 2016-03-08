package de.mvcturbine.breakout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.input.KeyboardInput;
import de.mvcturbine.breakout.input.MouseInput;
import de.mvcturbine.breakout.ui.desktop.DesktopWorldView;
import de.mvcturbine.breakout.ui.lighthouse.LighthouseWorldView;
import de.mvcturbine.breakout.world.WorldBreakout;

public class Main extends JFrame implements Runnable
{
	@Override
	public void run()
	{
		Dimension worldSize = new Dimension(20, 20);
		Breakout b = new Breakout();
		WorldBreakout world = new WorldBreakout(b, worldSize);
		b.addObserver(world);
		LighthouseWorldView lhView = new LighthouseWorldView(world);
		world.addObserver(lhView);
		if(!lhView.connect("10.42.0.135", 8000))
		{
			System.out.println("Failed to connect to lighthouse");
			System.exit(1);
		}
		DesktopWorldView view = new DesktopWorldView(world);
		world.addObserver(view);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Breakout");
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(500, 500));
		b.init();
		this.addKeyListener(new KeyboardInput(world));
		this.addMouseMotionListener(new MouseInput(world, this.getSize()));
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}
}
