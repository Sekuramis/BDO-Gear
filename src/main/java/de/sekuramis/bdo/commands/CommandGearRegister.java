package de.sekuramis.bdo.commands;

import de.sekuramis.bdo.mariadb.Database;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Sekuramis | Jannik
 */
public class CommandGearRegister extends ListenerAdapter
{
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		if (!"register".equals(event.getName())) return;

		long discordId = event.getUser().getIdLong();
		if (Database.isRegistered(discordId)) {
			event.reply("Bitte nutze die update funktion.").setEphemeral(true).queue();
			return;
		}

		String familyName = getOptionValue(event, "familyName", OptionMapping::getAsString);
		String charName = getOptionValue(event, "charName", OptionMapping::getAsString);
		String gameClass = getOptionValue(event, "class", OptionMapping::getAsString);
		int level = getOptionValue(event, "level", OptionMapping::getAsInt);
		int ap = getOptionValue(event, "ap", OptionMapping::getAsInt);
		int aap = getOptionValue(event, "aap", OptionMapping::getAsInt);
		int dp = getOptionValue(event, "dp", OptionMapping::getAsInt);
		String state = getOptionValue(event, "state", OptionMapping::getAsString);
		Message.Attachment proof = getOptionValue(event, "proof", OptionMapping::getAsAttachment);

		Database.registerMember(discordId, familyName, charName, gameClass, level, state, ap, aap, dp, proof.getUrl());
		event.reply("successfuly registered into gear database").setEphemeral(true).queue();
	}

	/**
	 * Hilfsmethode zum sicheren Abrufen eines Optionswertes und Konvertieren in den gewünschten Typ.
	 */
	private <T> T getOptionValue(SlashCommandInteractionEvent event, String optionName, Function<OptionMapping, T> converter) {
		return converter.apply(Objects.requireNonNull(event.getOption(optionName), "Missing option: " + optionName));
	}

}
