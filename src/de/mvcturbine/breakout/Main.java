package de.mvcturbine.breakout;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.input.KeyboardInput;
import de.mvcturbine.breakout.input.MouseInput;
import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.breakout.ui.desktop.DesktopWorldView;
import de.mvcturbine.breakout.ui.lighthouse.LightHouseTextView;
import de.mvcturbine.breakout.ui.lighthouse.LightHouseTextView.AnimationFinishedCallback;
import de.mvcturbine.breakout.ui.lighthouse.LightHouseView;
import de.mvcturbine.breakout.ui.lighthouse.LighthouseWorldView;
import de.mvcturbine.breakout.ui.lighthouse.render.font.LightHouseFontRender;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.WorldBreakout.GameCallback;

public class Main extends JFrame
		implements Runnable, GameCallback, AnimationFinishedCallback
{
	private LhNetwork lighthouse;
	private WorldBreakout world;
	private Breakout breakout;
	private LightHouseView lhview;
	private DesktopWorldView localView;
	private MouseMotionListener motionListener;
	private KeyListener keyListener;
	private Dimension worldSize = new Dimension(20, 20);

	@Override
	public void run()
	{
		this.breakout = new Breakout();
		this.lighthouse = new LhNetwork();
		if(!this.lighthouse.tryConnect("10.10.10.34", 8000))
			// if(!this.lighthouse.tryConnect("rtsys.informatik.uni-kiel.de",
			// 51122))
			System.err.println("Failed to connect to lighthouse");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Breakout");
		this.newGame();
		this.breakout.init();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}

	private void newGame()
	{
		if(this.world != null) this.breakout.deleteObserver(this.world);
		this.world = new WorldBreakout(this.breakout, this.worldSize);
		this.world.setCallback(this);
		this.breakout.addObserver(this.world);
		if(this.lighthouse.connected())
		{
			if(this.lhview != null) this.world.deleteObserver(lhview);
			this.lhview = new LighthouseWorldView(world, this.lighthouse);
			this.world.addObserver(this.lhview);
		}
		if(this.localView != null)
		{
			this.getContentPane().remove(this.localView);
			this.world.deleteObserver(this.localView);
		}
		this.localView = new DesktopWorldView(this.world);
		this.world.addObserver(this.localView);
		this.getContentPane().add(this.localView);
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(500, 500));
		if(this.motionListener != null)
			this.removeMouseMotionListener(this.motionListener);
		this.motionListener = new MouseInput(this.world, this.localView);
		this.addMouseMotionListener(this.motionListener);
		if(this.keyListener != null) this.removeKeyListener(this.keyListener);
		this.keyListener = new KeyboardInput(world);
		this.addKeyListener(this.keyListener);
	}

	@Override
	public void onWin(WorldBreakout world)
	{
		System.out.println("WIN");
		this.world.setCallback(null);
		if(this.lighthouse.connected())
		{
			this.world.deleteObserver(this.lhview);
			this.lhview = new LightHouseTextView(0.5d, "YOU WON!",
					new LightHouseFontRender(), this.world, this.lighthouse);
			((LightHouseTextView) this.lhview).setCallback(this);
			this.world.addObserver(this.lhview);
		}
		else
			newGame();
	}

	@Override
	public void onLoose(WorldBreakout world)
	{
		System.out.println("LOST");
		this.world.setCallback(null);
		if(this.lighthouse.connected())
		{
			this.world.deleteObserver(this.lhview);
			this.lhview = new LightHouseTextView(0.5d, "YOU LOST!",
					new LightHouseFontRender(), this.world, this.lighthouse);
			((LightHouseTextView) this.lhview).setCallback(this);
			this.world.addObserver(this.lhview);
		}
		else
			newGame();
	}

	@Override
	public void animationFinished(LightHouseTextView tv)
	{
		this.newGame();
	}
}
