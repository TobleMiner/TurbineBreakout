package de.mvcturbine.breakout.ui.desktop.render.entity.fx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import de.mvcturbine.breakout.world.entity.fx.EntityScoreParticle;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

/**
 * Renders score particles
 * 
 * @author tsys
 *
 */
public class ScoreParticleRender extends EntityRender
{
	HashMap<EntityScoreParticle, Color> colorMap = new HashMap<>();

	@Override
	protected void renderEntity(Entity e, BufferedImage img, Size2D scale)
	{
		EntityScoreParticle particle = (EntityScoreParticle) e;
		String label = "";

		Double difference = new Double(particle.getDiff());

		for(int i = 0; i < (new Double(particle.getWorld().getScore()).toString()
				.length()) - (difference.toString().length()); i++)
		{
			label += " ";
		}

		label += new Integer((int) particle.getDiff()).toString();

		Graphics2D gfx = (Graphics2D) img.getGraphics();

		if(colorMap.get(particle) == null)
		{
			colorMap.put(particle,
					new Color(particle.getWorld().getGame().rand.nextInt(2 << 24)));
		}
		gfx.setColor(colorMap.get(particle));

		gfx.setFont(new Font("Monospaced", Font.PLAIN,
				(int) (particle.getSize().getHeight() * scale.getY())));

		gfx.drawString(label, (int) (particle.getLocation().getX() * scale.getX()),
				(int) (img.getHeight() - particle.getLocation().getY() * scale.getY()));
	}

}
