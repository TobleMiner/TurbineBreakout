package de.mvcturbine.breakout.ui.lighthouse;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

import de.mvcturbine.breakout.network.lighthouse.LhNetwork;
import de.mvcturbine.breakout.ui.lighthouse.render.entity.BallRender;
import de.mvcturbine.breakout.ui.lighthouse.render.entity.BlockRender;
import de.mvcturbine.breakout.ui.lighthouse.render.entity.GenericEntityPixelRender;
import de.mvcturbine.breakout.ui.lighthouse.render.entity.PaddleRender;
import de.mvcturbine.breakout.world.WorldBreakout;
import de.mvcturbine.breakout.world.entity.EntityBall;
import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.breakout.world.entity.EntityPaddle;
import de.mvcturbine.breakout.world.entity.fx.EntityScore;
import de.mvcturbine.breakout.world.entity.fx.EntityScoreParticle;
import de.mvcturbine.ui.render.RenderRegistry;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.World;
import de.mvcturbine.world.entity.Entity;

/**
 * View to display a {@link WorldBreakout} on the lighthouse
 * 
 * @author tsys
 *
 */
public class LighthouseWorldView extends LightHouseView
{
	/** Registry for renders */
	private final RenderRegistry renderRegistry;

	/** The world of this view */
	private final World world;

	/**
	 * Constructs a new LighthouseWorldView
	 * 
	 * @param world
	 *            The world this view displays
	 * @param lighthouse
	 */
	public LighthouseWorldView(WorldBreakout world, LhNetwork lighthouse)
	{
		super(lighthouse);
		this.world = world;
		this.renderRegistry = new RenderRegistry();
		this.renderRegistry.registerRender(new GenericEntityPixelRender(), Entity.class);
		this.renderRegistry.registerRender(new PaddleRender(), EntityPaddle.class);
		this.renderRegistry.registerRender(new BlockRender(), EntityBlock.class);
		this.renderRegistry.registerRender(new BallRender(), EntityBall.class);
		this.renderRegistry.registerRender(null, EntityScore.class);
		this.renderRegistry.registerRender(null, EntityScoreParticle.class);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(this.lighthouse.connected())
		{
			Dimension size = this.world.getSize();
			Size2D lhSize = this.lighthouse.getSize();
			Size2D scale = new Size2D(lhSize.width / size.width,
					lhSize.height / size.height);
			BufferedImage img = new BufferedImage((int) lhSize.width, (int) lhSize.height,
					BufferedImage.TYPE_4BYTE_ABGR);
			for(Entity ent : new ArrayList<Entity>(this.world.getAllEntities()))
			{
				assert ent != null;
				EntityRender render = (EntityRender) this.renderRegistry
						.getRender(ent.getClass());
				if(render != null) render.render(ent, img, scale);
			}
			lighthouse.setFrame(img);
		}
	}
}
