package de.sekuramis.bdo.utils;

import de.sekuramis.bdo.GearBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

/**
 * @author Sekuramis | Jannik
 */
public class DiscordModifier
{
	private static final JDA jda = GearBot.getJda();

	public static TextChannel gearChannel = jda.getTextChannelById(1354176006252007527L);
}
