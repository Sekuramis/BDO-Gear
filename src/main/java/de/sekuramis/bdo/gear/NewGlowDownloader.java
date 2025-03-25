package de.sekuramis.bdo.gear;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sekuramis | Jannik
 */
public class NewGlowDownloader
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

	// Mapping der Klassen zu ihren Banner-URLs (wie in deinem classes.txt)
	static final Map<String, String> BANNER_URLS = new HashMap<>();
	static {
		BANNER_URLS.put("warrior",    "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_0_top_symbol.png");
		BANNER_URLS.put("ranger",     "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_4_top_symbol.png");
		BANNER_URLS.put("sorceress",  "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_8_top_symbol.png");
		BANNER_URLS.put("berserker",  "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_12_top_symbol.png");
		BANNER_URLS.put("tamer",      "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_16_top_symbol.png");
		BANNER_URLS.put("musa",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_20_top_symbol.png");
		BANNER_URLS.put("maehwa",     "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_21_top_symbol.png");
		BANNER_URLS.put("valkyrie",   "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_24_top_symbol.png");
		BANNER_URLS.put("kunoichi",   "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_25_top_symbol.png");
		BANNER_URLS.put("ninja",      "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_26_top_symbol.png");
		BANNER_URLS.put("wizard",     "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_28_top_symbol.png");
		BANNER_URLS.put("witch",      "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_31_top_symbol.png");
		BANNER_URLS.put("dark_knight","https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_27_top_symbol.png");
		BANNER_URLS.put("striker",    "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_19_top_symbol.png");
		BANNER_URLS.put("mystic",     "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_23_top_symbol.png");
		BANNER_URLS.put("lahn",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_11_top_symbol.png");
		BANNER_URLS.put("archer",     "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_29_top_symbol.png");
		BANNER_URLS.put("shai",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_17_top_symbol.png");
		BANNER_URLS.put("guardian",   "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_5_top_symbol.png");
		BANNER_URLS.put("hashashin",  "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_1_top_symbol.png");
		BANNER_URLS.put("nova",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_9_top_symbol.png");
		BANNER_URLS.put("sage",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_2_top_symbol.png");
		BANNER_URLS.put("corsair",    "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_10_top_symbol.png");
		BANNER_URLS.put("drakania",   "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_7_top_symbol.png");
		BANNER_URLS.put("woosa",      "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_30_top_symbol.png");
		BANNER_URLS.put("maegu",      "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_15_top_symbol.png");
		BANNER_URLS.put("scholar",    "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_6_top_symbol.png");
		BANNER_URLS.put("dosa",       "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_33_top_symbol.png");
		BANNER_URLS.put("deadeye",    "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/character_34_top_symbol.png");
	}

	public static void main(String[] args) {
		// Das Spritesheet mit den weißen Icons
		String spriteUrl = "https://s1.pearlcdn.com/NAEU/contents/img/portal/gameinfo/classes_symbol_spr.png?v=1";
		String outputDirPath = "icons_classes_glow/";

		try {
			BufferedImage sprite = ImageIO.read(new URL(spriteUrl));
			File outputDir = new File(outputDirPath);
			if (!outputDir.exists()) {
				outputDir.mkdirs();
			}

			// Für jede Klasse:
			for (int i = 0; i < ROWS; i++) {
				int y = i * ICON_SIZE;
				String baseName = i < CLASS_NAMES.length ? CLASS_NAMES[i] : "class_" + i;

				// Weißes Icon (linke Spalte) aus dem Spritesheet holen
				BufferedImage whiteIcon = sprite.getSubimage(0, y, ICON_SIZE, ICON_SIZE);

				// Banner-URL zur aktuellen Klasse abrufen
				String bannerUrl = BANNER_URLS.get(baseName);
				Color dominantColor = null;

				if (bannerUrl != null) {
					try {
						BufferedImage banner = ImageIO.read(new URL(bannerUrl));
						dominantColor = getDominantColor(banner);
					} catch (IOException e) {
						System.err.println("Fehler beim Laden des Banners für " + baseName + ": " + e.getMessage());
					}
				}

				// Fallback: falls kein Banner geladen werden konnte, nutze Blau
				if (dominantColor == null) {
					dominantColor = new Color(0, 150, 255);
				}

				// Farbsättigung & Helligkeit stärker anheben
				float[] hsb = Color.RGBtoHSB(dominantColor.getRed(), dominantColor.getGreen(), dominantColor.getBlue(), null);
				// +50% Sättigung
				hsb[1] = Math.min(1.0f, hsb[1] * 1.5f);
				// +20% Helligkeit
				hsb[2] = Math.min(1.0f, hsb[2] * 1.2f);

				Color saturatedColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
				// Volle Deckkraft für intensiveren Glow
				Color glowColor = new Color(saturatedColor.getRed(), saturatedColor.getGreen(), saturatedColor.getBlue(), 255);

				// Glow-Effekt anwenden
				BufferedImage glowing = addGlowEffect(whiteIcon, GLOW_SIZE, glowColor);

				// Speichern
				File outputFile = new File(outputDir, baseName + "_glow.png");
				ImageIO.write(glowing, "png", outputFile);

				// Nachricht ausgeben, sobald ein Icon fertig ist
				System.out.println(baseName + " downloaded");
			}

			System.out.println("Icons mit stärkerem, farblich angepasstem Glow gespeichert in: " + outputDirPath);

		} catch (IOException e) {
			System.err.println("Fehler beim Verarbeiten: " + e.getMessage());
		}
	}

	/**
	 * Erzeugt einen einfachen "Glow" durchs Mehrfachzeichnen des Icons mit abnehmender Deckkraft
	 * und kleinem Offset. Erhöht den Schein-Effekt um das Bild herum.
	 */
	public static BufferedImage addGlowEffect(BufferedImage src, int glowSize, Color glowColor) {
		// Neues Bild anlegen, damit der Glow nicht abgeschnitten wird
		int newSize = src.getWidth() + glowSize * 2;
		BufferedImage glowImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = glowImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Mehrfaches Zeichnen des getönten Bilds
		// => Größere Alpha-Basis => stärkerer Glow
		for (int i = glowSize; i > 0; i--) {
			// alpha reicht hier von 1.0 (stark) bis 1/8 (leicht)
			float alpha = (float) i / (float) glowSize;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(tintImage(src, glowColor), i, i, null);
		}

		// Originalbild in der Mitte zeichnen (volle Deckkraft)
		g2.setComposite(AlphaComposite.SrcOver);
		g2.drawImage(src, glowSize, glowSize, null);

		g2.dispose();
		return glowImage;
	}

	/**
	 * Färbt das gegebene Bild komplett in der angegebenen Farbe ein (unter Beibehaltung der Alpha-Form).
	 */
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

	/**
	 * Ermittelt die dominante Farbe in einem Bild (häufigster RGB-Wert, ignoriert Transparenz).
	 */
	public static Color getDominantColor(BufferedImage image) {
		Map<Integer, Integer> colorCount = new HashMap<>();
		int width = image.getWidth();
		int height = image.getHeight();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int argb = image.getRGB(x, y);
				Color color = new Color(argb, true);
				// transparente Pixel überspringen
				if (color.getAlpha() < 128) continue;

				// Nur RGB
				int rgb = argb & 0x00FFFFFF;
				colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
			}
		}

		int dominantRGB = 0;
		int maxCount = 0;
		for (Map.Entry<Integer, Integer> entry : colorCount.entrySet()) {
			if (entry.getValue() > maxCount) {
				dominantRGB = entry.getKey();
				maxCount = entry.getValue();
			}
		}
		return new Color(dominantRGB);
	}
}
