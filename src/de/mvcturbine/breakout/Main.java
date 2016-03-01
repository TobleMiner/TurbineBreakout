package de.mvcturbine.breakout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.mvcturbine.breakout.game.Breakout;
import de.mvcturbine.breakout.ui.render.TestGameRender;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.util.geom.Vec2D;

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
		// System.out.println(new Vec2D(1, 1).getAngle() / Math.PI * 180);
		Vec2D vec1 = new Vec2D(1, 1);
		Vec2D vec2 = new Vec2D(-1, 0);
		System.out.println(vec1.getAngle() / Math.PI * 180);
		System.out.println(vec2.getAngle() / Math.PI * 180);
		// System.out.println(vec.setAngle(vec.getAngle()).getAngle() / Math.PI
		// * 180);
		// System.out.println(new Vec2D(-1, 1).angle() / Math.PI * 180);
		// System.out.println(new Vec2D(-1, -1).angle() / Math.PI * 180);
		// System.out.println(new Vec2D(1, -1).angle() / Math.PI * 180);
		// Line2D line1 = new Line2D(new Loc2D(), new Vec2D(1, 1));
		// Line2D line2 = new Line2D(new Loc2D(0, 1), new Vec2D(1, -1));
		// System.out.println(line1.intersection(line2).toString());
		// System.exit(0);
		SwingUtilities.invokeLater(new Main());
	}
}
