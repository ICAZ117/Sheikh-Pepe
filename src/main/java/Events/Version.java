package Events;

import Main.Main;
import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Version extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// Check the command type
		if (event.getName().equals("version") && !event.getUser().isBot()) {
			// Send waiting message
			event.deferReply().queue();

			Main.log("-> Command \">version\" executed by " + event.getUser().getName());

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			// Set embed title
			eb.setAuthor("Bot Version", null);

			// Set embed description
			eb.setDescription(Main.class.getPackage().getImplementationVersion());

			// Create embed
			MessageEmbed embed = eb.build();

			event.getHook().sendMessageEmbeds(embed).queue();
		}
	}

}
