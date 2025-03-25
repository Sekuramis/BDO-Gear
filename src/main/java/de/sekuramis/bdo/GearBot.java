package de.sekuramis.bdo;

import de.sekuramis.bdo.utils.ConfigManager;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

/**
 * @author Sekuramis | Jannik
 */
public class GearBot
{
	@Getter
	private static JDA jda;
	@Getter
	private static Guild guild;
	@Getter
	private static ConfigManager config = new ConfigManager("/opt/guilds/VERSUS/token.yml");

	public static void main(String[] args) throws InterruptedException
	{
		setupBOT();
	}

	private static void setupBOT() throws InterruptedException
	{
		JDABuilder builder = JDABuilder.createDefault(config.getToken(), EnumSet.allOf(GatewayIntent.class));
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setAutoReconnect(true);
		builder.setActivity(Activity.watching("Your Gear"));
		builder.enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ACTIVITY);
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);

		jda =  builder.build().awaitReady();
		guild = jda.awaitReady().getGuildById(1352313465506627744L);
	}

	private void loadListener()
	{
		@NotNull Guild server = guild;
	}

	private void loadCommands()
	{

	}

	private void connectDatabase()
	{

	}
}
