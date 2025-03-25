package de.sekuramis.bdo.gear;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sekuramis | Jannik
 */
public class ImageDownloader
{
	static final int ICON_SIZE = 50;
	static final int ROWS = 29;

	static final String[] CLASS_NAMES = {
			"warrior", "ranger", "sorceress", "berserker",
			"tamer", "musa", "maehwa", "valkyrie",
			"kunoichi", "ninja", "wizard", "witch",
			"dark_knight", "striker", "mystic", "lahn",
			"archer", "shai", "guardian", "hashashin",
			"nova", "sage", "corsair", "drakania",
			"woosa", "maegu", "scholar", "dosa", "deadeye" // ggf. anpassen
	};

	public static void main(String[] args) {
		String imageUrl = "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/classes_symbol_spr.png?v=1";
		String outputDirPath = "bdo_icons/";

		try {
			BufferedImage sprite = ImageIO.read(new URL(imageUrl));
			File outputDir = new File(outputDirPath);
			if (!outputDir.exists()) outputDir.mkdirs();

			for (int i = 0; i < ROWS; i++) {
				int y = i * ICON_SIZE;

				String baseName = i < CLASS_NAMES.length ? CLASS_NAMES[i] : "class_" + i;

				// Weiße Version (linke Spalte)
				BufferedImage whiteIcon = sprite.getSubimage(0, y, ICON_SIZE, ICON_SIZE);
				File whiteFile = new File(outputDir, baseName + "_white.png");
				ImageIO.write(whiteIcon, "png", whiteFile);

				// Gelbe Version (rechte Spalte)
				BufferedImage yellowIcon = sprite.getSubimage(ICON_SIZE, y, ICON_SIZE, ICON_SIZE);
				File yellowFile = new File(outputDir, baseName + "_yellow.png");
				ImageIO.write(yellowIcon, "png", yellowFile);
			}

			System.out.println("Alle Icons erfolgreich extrahiert in: " + outputDirPath);

		} catch (IOException e) {
			System.err.println("Fehler beim Verarbeiten: " + e.getMessage());
		}
	}
}
