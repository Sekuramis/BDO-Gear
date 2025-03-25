package de.sekuramis.bdo.utils;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author Sekuramis | Jannik
 */
public class ConfigManager
{
	@Getter
	private final String path;
	@Getter
	private Map<String, Object> configData;

	public ConfigManager(String path)
	{
		this.path = path;
		loadConfig();
	}

	private void loadConfig()
	{
		try
		{
			File configFile = new File(path);
			if(!configFile.exists())
			{
				throw new IOException("Not found: " + path);
			}

			Yaml yaml = new Yaml();
			try(FileReader reader = new FileReader(configFile))
			{
				configData = yaml.load(reader);
			}

			if(configData == null)
			{
				throw new IllegalStateException("empty file");
			}

		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public String getToken()
	{
		Object token = configData.get("Token");
		if(token == null)
		{
			throw new IllegalStateException("token not found");
		}

		return token.toString();
	}

	public String getUsername()
	{
		Object username = configData.get("username");
		if(username == null)
		{
			throw new IllegalStateException("username not found");
		}

		return username.toString();
	}

	public String getPassword()
	{
		Object password = configData.get("password");
		if(password == null)
		{
			throw new IllegalStateException("password not found");
		}

		return password.toString();
	}
}
