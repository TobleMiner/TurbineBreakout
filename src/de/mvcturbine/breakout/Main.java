package de.mvcturbine.breakout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.input.KeyboardInput;
import de.mvcturbine.breakout.input.MouseInput;
import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.breakout.ui.desktop.DesktopWorldView;
import de.mvcturbine.breakout.ui.lighthouse.LightHouseTextView;
import de.mvcturbine.breakout.ui.lighthouse.LighthouseWorldView;
import de.mvcturbine.breakout.ui.lighthouse.render.font.LightHouseFontRender;
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
		LhNetwork lighthouse = new LhNetwork();
		LighthouseWorldView lhView = new LighthouseWorldView(world, lighthouse);
		LightHouseTextView tv = new LightHouseTextView(0.5d, "HELLO WORLD",
				new LightHouseFontRender(), b, lighthouse);
		world.addObserver(lhView);
		if(false && !lhView.connect("lighthouse.vm.local", 8000))
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
		this.addMouseMotionListener(new MouseInput(world, view));
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}
}
