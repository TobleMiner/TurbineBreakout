package de.mvcturbine.breakout.ui.desktop.render.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.mvcturbine.breakout.world.entity.EntityBlock;
import de.mvcturbine.ui.render.entity.EntityRender;
import de.mvcturbine.util.geom.Size2D;
import de.mvcturbine.world.entity.Entity;

public class BlockImageRender extends EntityRender {

	private static BufferedImage[] textures = new BufferedImage[EntityBlock.MAX_DURABILITY];

	private static HashMap<Integer, Image[]> scaled = new HashMap<>();

	public BlockImageRender() {
		try {
			textures[0] = ImageIO
					.read(this.getClass().getResourceAsStream("/de/mvcturbine/breakout/textures/blockYellow.png"));
			textures[1] = ImageIO
					.read(this.getClass().getResourceAsStream("/de/mvcturbine/breakout/textures/blockGreen.png"));
			textures[2] = ImageIO
					.read(this.getClass().getResourceAsStream("/de/mvcturbine/breakout/textures/blockRed.png"));
			textures[3] = ImageIO
					.read(this.getClass().getResourceAsStream("/de/mvcturbine/breakout/textures/blockBlue.png"));
			// textures[4] = ImageIO
			// .read(this.getClass().getResourceAsStream("/de/mvcturbine/breakout/textures/blockBlue.png"));

		} catch (IOException e) {
			System.out.println("textures not found");
		}

	}

	@Override
	public void renderEntity(Entity e, BufferedImage img, Size2D scale) {
		EntityBlock block = (EntityBlock) e;
		Graphics2D gfx = (Graphics2D) img.getGraphics();

		int imgSize = img.getWidth() * img.getHeight();

		if (scaled.get(imgSize) == null) {
			Image[] scaledImg = new Image[EntityBlock.MAX_DURABILITY];

			double width = block.getSize().getWidth() * scale.getX();
			double height = block.getSize().getHeight() * scale.getY();

			for (int i = 0; i < EntityBlock.MAX_DURABILITY; i++) {
				Image displayedImage = textures[i].getScaledInstance((int) width, (int) height,
						BufferedImage.SCALE_FAST);
				scaledImg[i] = displayedImage;
			}

			scaled.put(imgSize, scaledImg);
		}

		gfx.drawImage(scaled.get(imgSize)[block.getDurability() - 1], (int) (block.getLocation().getX() * scale.getX()),
				img.getHeight() - (int) ((block.getSize().getHeight() + block.getLocation().getY()) * scale.getY()),
				null);

	}
}
