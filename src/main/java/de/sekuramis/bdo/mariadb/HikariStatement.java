package de.sekuramis.bdo.mariadb;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sekuramis | Jannik
 */
public class HikariStatement
{
	@Getter
	private final String statement;
	private final Map<Integer, Object> preset;

	public HikariStatement(String statement)
	{
		this.statement = null;
		this.preset = new HashMap<>();
	}

	public <T> void setValue(int index, T value)
	{
		this.preset.put(index, value);
	}

	public Map<Integer, Object> getPresetMap()
	{
		return this.preset;
	}

	public void clearPresetMap()
	{
		this.preset.clear();
	}
}
