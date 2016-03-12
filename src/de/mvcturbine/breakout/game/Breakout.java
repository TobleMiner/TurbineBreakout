package de.mvcturbine.breakout.game;

import java.awt.Dimension;

import javax.swing.JFrame;

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
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.level.LevelGenerator;
import de.mvcturbine.game.Game;
import de.mvcturbine.world.entity.Entity;

public class Breakout extends Game implements GameCallback, AnimationFinishedCallback
{
	private LhNetwork lighthouse;
	private WorldBreakout world;
	private LightHouseView lhview;
	private Dimension worldSize = new Dimension(20, 20);
	private JFrame app;

	public Breakout(JFrame app)
	{
		super();
		this.app = app;
	}

	@Override
	public void init()
	{
		super.init();
		this.lighthouse = new LhNetwork();
		this.world = new WorldBreakout(this, this.worldSize);
		this.world.setCallback(this);
		this.addObserver(this.world);
		// Try to connect to lighthouse
		if(!this.lighthouse.tryConnect("lighthouse.vm.local", 8000))
			// if(!this.lighthouse.tryConnect("rtsys.informatik.uni-kiel.de",
			// 51122))
			System.err.println("Failed to connect to lighthouse");
		this.lighthouse.setSimulation(true);
		// Setup desktop view (Probably not the best place to do it, but it's
		// 5:30 am) TODO
		DesktopWorldView localView = new DesktopWorldView(this.world);
		MouseInput motionListener = new MouseInput(this.world, localView);
		this.app.addMouseMotionListener(motionListener);
		KeyboardInput keyListener = new KeyboardInput(world);
		this.app.addKeyListener(keyListener);
		this.app.getContentPane().add(localView);
		this.app.pack();
		this.app.setSize(new Dimension(500, 500));
		this.app.invalidate();
		this.app.setVisible(true);
		this.world.addObserver(localView);
		this.newGame();
	}

	@Override
	public int getTPS()
	{
		return 60;
	}

	@Override
	protected void tick()
	{
		super.tick();
	}

	/**
	 * Starts a new game
	 */
	private void newGame()
	{
		this.stop();
		for(Entity e : this.world.getAllEntities())
		{
			if(e instanceof EntityBlock)
			{
				this.world.deleteObserver(e);
			}
		}
		this.world.resetWorld();
		this.world.setCallback(this);
		LevelGenerator generator = new LevelGenerator(world);
		generator.populateWorld();
		if(this.lighthouse.connected())
		{
			if(this.lhview != null) this.world.deleteObserver(lhview);
			this.lhview = new LighthouseWorldView(world, this.lighthouse);
			this.world.addObserver(this.lhview);
		}
		this.start();
	}

	@Override
	public void onWin(WorldBreakout world)
	{
		this.world.removeBall();
		this.world.setCallback(null);
		if(this.lighthouse.connected())
		{
			this.world.deleteObserver(this.lhview);
			this.lhview = new LightHouseTextView(0.5d, "YOU WIN!",
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
		newGame();
	}
}
