package de.mvcturbine.breakout.ui.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

import de.mvcturbine.breakout.ui.desktop.render.entity.BlockColorRender;
import de.mvcturbine.breakout.ui.desktop.render.entity.GenericEntityMeshRender;
import de.mvcturbine.breakout.ui.desktop.render.entity.PaddleRender;
import de.mvcturbine.breakout.ui.desktop.render.entity.fx.Laz0rBeamRender;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.fx.EntityLaz0rBeam;
import de.mvcturbine.ui.View;
import de.mvcturbine.ui.render.RenderRegistry;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

public class DesktopWorldView extends JPanel implements View
{
	private final RenderRegistry renderRegistry;
	private final World world;

	public DesktopWorldView(WorldBreakout world)
	{
		super();
		this.world = world;
		this.renderRegistry = new RenderRegistry();
		this.renderRegistry.registerRender(new GenericEntityMeshRender(), Entity.class);
		this.renderRegistry.registerRender(new BlockColorRender(), EntityBlock.class);
		this.renderRegistry.registerRender(new Laz0rBeamRender(), EntityLaz0rBeam.class);
		this.renderRegistry.registerRender(new PaddleRender(), EntityPaddle.class);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Dimension size = this.world.getSize();
		int width = getWidth();
		int height = getHeight();
		Size2D scale = new Size2D((double) width / size.width,
				(double) height / size.height);
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.clearRect(0, 0, width, height);
		for(Entity ent : new ArrayList<Entity>(this.world.getAllEntities()))
		{
			EntityRender render = (EntityRender) this.renderRegistry
					.getRender(ent.getClass());
			if(render != null) render.renderEntity(ent, img, scale);
		}
		g.drawImage(img, 0, 0, null);
	}
}
