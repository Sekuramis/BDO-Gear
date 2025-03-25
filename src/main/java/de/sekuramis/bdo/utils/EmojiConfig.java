package de.sekuramis.bdo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Sekuramis | Jannik
 */
@Getter
@AllArgsConstructor
public enum EmojiConfig
{
	WARRIOR("warrior_glow", 1352619931476168704L),
	RANGER("ranger_glow", 1352619789788381277L),
	SORCERESS("sorceress_glow", 1352619817852731403L),
	BERSERKER("berserker_glow", 1352598091747954698L),
	TAMER("tamer_glow", 1352619907241742356L),
	MUSA("musa_glow", 1352619760658944081L),
	MAEHWA("maehwa_glow", 1352619731588481086L),
	VALKYRIE("valkyrie_glow", 1352619909229838478L),
	KUNOICHI("kunoichi_glow", 1352619702651846720L),
	NINJA("ninja_glow", 1352619763687227484L),
	WIZARD("wizard_glow", 1352619933913317419L),
	WITCH("witch_glow", 1352619932738916494L),
	STRIKER("striker_glow", 1352619905689583616L),
	MYSTIC("mystic_glow", 1352619762139791402L),
	LAHN("lahn_glow", 1352619728732033084L),
	ARCHER("archer_glow", 1352598090464628779L),
	DARK_KNIGHT("dark_knight_glow", 1352612121640304672L),
	SHAI("shai_glow", 1352619816292323350L),
	GUARDIAN("guardian_glow", 1352619699866964060L),
	HASHASHIN("hashashin_glow", 1352619701112668290L),
	NOVA("nova_glow", 1352619788584747018L),
	SAGE("sage_glow", 1352619791038283806L),
	CORSAIR("corsair_glow", 1352612124878311518L),
	DRAKANIA("drakania_glow", 1352612137792700447L),
	WOOSA("woosa_glow", 1352612127294357585L),
	MAEGU("maegu_glow", 1352619730145378434L),
	SCHOLAR("scholar_glow", 1352619814593495052L),
	DOSA("dosa_glow", 1352612136165052426L),
	DEADEYE("deadeye_glow", 1352612134978060308L);

	private final String name;
	private final long id;
}
