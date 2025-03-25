package de.sekuramis.bdo.gear;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sekuramis | Jannik
 */
public class ImageGlowDownloader
{
	static final int ICON_SIZE = 50;
	static final int ROWS = 29;
	static final int GLOW_SIZE = 8;

	static final String[] CLASS_NAMES = {
			"warrior", "ranger", "sorceress", "berserker",
			"tamer", "musa", "maehwa", "valkyrie",
			"kunoichi", "ninja", "wizard", "witch",
			"dark_knight", "striker", "mystic", "lahn",
			"archer", "shai", "guardian", "hashashin",
			"nova", "sage", "corsair", "drakania",
			"woosa", "maegu", "scholar", "dosa", "deadeye"
	};

	public static void main(String[] args) {
		String imageUrl = "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/classes_symbol_spr.png?v=1";
		String outputDirPath = "bdo_icons_glow/";

		try {
			BufferedImage sprite = ImageIO.read(new URL(imageUrl));
			File outputDir = new File(outputDirPath);
			if (!outputDir.exists()) outputDir.mkdirs();

			for (int i = 0; i < ROWS; i++) {
				int y = i * ICON_SIZE;

				String baseName = i < CLASS_NAMES.length ? CLASS_NAMES[i] : "class_" + i;

				// Weißes Icon holen (linke Spalte)
				BufferedImage whiteIcon = sprite.getSubimage(0, y, ICON_SIZE, ICON_SIZE);

				// Glow anwenden
				BufferedImage glowing = addGlowEffect(whiteIcon, GLOW_SIZE, new Color(0, 150, 255, 180));

				// Speichern
				File outputFile = new File(outputDir, baseName + "_glow.png");
				ImageIO.write(glowing, "png", outputFile);
			}

			System.out.println("Icons mit Glow gespeichert in: " + outputDirPath);

		} catch (IOException e) {
			System.err.println("Fehler beim Verarbeiten: " + e.getMessage());
		}
	}

	public static BufferedImage addGlowEffect(BufferedImage src, int glowSize, Color glowColor) {
		int newSize = src.getWidth() + glowSize * 2;
		BufferedImage glowImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = glowImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Weicher Schatten durch mehrfaches Zeichnen
		for (int i = glowSize; i > 0; i--) {
			float alpha = (float) i / (glowSize * 2);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(tintImage(src, glowColor), i, i, null);
		}

		// Originalbild in der Mitte zeichnen
		g2.setComposite(AlphaComposite.SrcOver);
		g2.drawImage(src, glowSize, glowSize, null);
		g2.dispose();

		return glowImage;
	}

	public static BufferedImage tintImage(BufferedImage src, Color color) {
		BufferedImage tinted = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = tinted.createGraphics();
		g2d.drawImage(src, 0, 0, null);
		g2d.setComposite(AlphaComposite.SrcAtop);
		g2d.setColor(color);
		g2d.fillRect(0, 0, src.getWidth(), src.getHeight());
		g2d.dispose();
		return tinted;
	}
}
