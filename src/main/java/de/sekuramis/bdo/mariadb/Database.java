package de.sekuramis.bdo.mariadb;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sekuramis | Jannik
 */
public abstract class Database
{
	public static HikariPool mysql = null;

	//DISCORD ID
	public static boolean isRegistered(long discordID)
	{
		final HikariStatement hikariStatement = new HikariStatement("SELECT 1 FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);

		return mysql.isInDatabase(hikariStatement);
	}

	public static String getFamilyName(long discordID)
	{
		if(!isRegistered(discordID)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT FAMILY_NAME FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getString(hikariStatement, "FAMILY_NAME");
	}

	public static String getChracterName(long discordID)
	{
		if(!isRegistered(discordID)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT CHARACTER_NAME FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getString(hikariStatement, "CHARACTER_NAME");
	}

	public static Integer getLevel(long discordID)
	{
		if(!isRegistered(discordID)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT LEVEL FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getInt(hikariStatement, "LEVEL");
	}

	public static String getState(long discordID)
	{
		if(!isRegistered(discordID)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT STATE FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getString(hikariStatement, "STATE");
	}

	public static Integer getAP(long discordID)
	{
		if(!isRegistered(discordID)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AP FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getInt(hikariStatement, "AP");
	}

	public static Integer getAAP(long discordID)
	{
		if(!isRegistered(discordID)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AAP FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getInt(hikariStatement, "AAP");
	}

	public static Integer getDP(long discordID)
	{
		if(!isRegistered(discordID)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT DP FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getInt(hikariStatement, "DP");
	}

	public static String getProof(long discordID)
	{
		if(!isRegistered(discordID)) return "https://media.tenor.com/ZPCxZ_bcWysAAAAi/you-have-no-proof-tolkien-black.gif";

		final HikariStatement hikariStatement = new HikariStatement("SELECT PROOF FROM gear_stats WHERE DISCORD_ID = ?");
		hikariStatement.setValue(1, discordID);
		return mysql.getString(hikariStatement, "PROOF");
	}

	//FAMILYNAME
	public static boolean isRegistered(@NotNull String familyName)
	{
		final HikariStatement hikariStatement = new HikariStatement("SELECT 1 FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);

		return mysql.isInDatabase(hikariStatement);
	}

	public static long getDiscordID(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return 0L;

		final HikariStatement hikariStatement = new HikariStatement("SELECT DISCORD_ID FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getLong(hikariStatement, "DISCORD_ID");
	}

	public static String getChracterName(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT CHARACTER_NAME FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getString(hikariStatement, "CHARACTER_NAME");
	}

	public static Integer getLevel(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT LEVEL FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getInt(hikariStatement, "LEVEL");
	}

	public static String getState(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT STATE FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getString(hikariStatement, "STATE");
	}

	public static Integer getAP(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AP FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getInt(hikariStatement, "AP");
	}

	public static Integer getAAP(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AAP FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getInt(hikariStatement, "AAP");
	}

	public static Integer getDP(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT DP FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getInt(hikariStatement, "DP");
	}

	public static String getProof(@NotNull String familyName)
	{
		if(!isRegistered(familyName)) return "https://media.tenor.com/ZPCxZ_bcWysAAAAi/you-have-no-proof-tolkien-black.gif";

		final HikariStatement hikariStatement = new HikariStatement("SELECT PROOF FROM gear_stats WHERE FAMILY_NAME = ?");
		hikariStatement.setValue(1, familyName);
		return mysql.getString(hikariStatement, "PROOF");
	}

	//CHARACTERNAME
	public static boolean isRegisteredCharname(String characterName)
	{
		final HikariStatement hikariStatement = new HikariStatement("SELECT 1 FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);

		return mysql.isInDatabase(hikariStatement);
	}

	public static long getDiscordIDbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return 0L;

		final HikariStatement hikariStatement = new HikariStatement("SELECT DISCORD_ID FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getLong(hikariStatement, "DISCORD_ID");
	}

	public static String getFamilyNamebyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT FAMILY_NAME FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getString(hikariStatement, "FAMILY_NAME");
	}

	public static Integer getLevelbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT LEVEL FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getInt(hikariStatement, "LEVEL");
	}

	public static String getStatebyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return "NONE";

		final HikariStatement hikariStatement = new HikariStatement("SELECT STATE FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getString(hikariStatement, "STATE");
	}

	public static Integer getAPbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AP FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getInt(hikariStatement, "AP");
	}

	public static Integer getAAPbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT AAP FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getInt(hikariStatement, "AAP");
	}

	public static Integer getDPbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return 0;

		final HikariStatement hikariStatement = new HikariStatement("SELECT DP FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getInt(hikariStatement, "DP");
	}

	public static String getProofbyCharname(@NotNull String characterName)
	{
		if(!isRegisteredCharname(characterName)) return null;

		final HikariStatement hikariStatement = new HikariStatement("SELECT PROOF FROM gear_stats WHERE CHARACTER_NAME = ?");
		hikariStatement.setValue(1, characterName);
		return mysql.getString(hikariStatement, "PROOF");
	}

	//FUNCTIONS
	public static void registerMember(long discordID, String familyName, String characterName, String gameClass, int level, String state, int ap, int aap, int dp, String proof)
	{
		if(isRegistered(discordID)) return;

		final HikariStatement hikariStatement = new HikariStatement("INSERT INTO gear_stats(DISCORD_ID,FAMILY_NAME,CHARACTER_NAME,CLASS,LEVEL,STATE,AP,AAP,DP,PROOF) VALUES(?,?,?,?,?,?,?,?,?,?)");
		hikariStatement.setValue(1, discordID);
		hikariStatement.setValue(2, familyName);
		hikariStatement.setValue(3, characterName);
		hikariStatement.setValue(4, gameClass);
		hikariStatement.setValue(5, level);
		hikariStatement.setValue(6, state);
		hikariStatement.setValue(7, ap);
		hikariStatement.setValue(8, aap);
		hikariStatement.setValue(9, dp);
		hikariStatement.setValue(10, proof);
		mysql.runASyncUpdate(hikariStatement);
	}

	public static void updateMember(long discordID, Map<String, Object> updates) {
		if (!isRegistered(discordID)) return;

		if (updates == null || updates.isEmpty()) {
			return;
		}

		StringBuilder sql = new StringBuilder("UPDATE gear_stats SET ");
		List<Object> parameters = new ArrayList<>();

		for (Map.Entry<String, Object> entry : updates.entrySet()) {
			sql.append(entry.getKey()).append(" = ?, ");
			parameters.add(entry.getValue());
		}

		sql.setLength(sql.length() - 2);
		sql.append(" WHERE DISCORD_ID = ?");
		parameters.add(discordID);

		final HikariStatement statement = new HikariStatement(sql.toString());
		for (int i = 0; i < parameters.size(); i++) {
			statement.setValue(i + 1, parameters.get(i));
		}

		mysql.runASyncUpdate(statement);
	}

}
