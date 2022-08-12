package Events;

import Main.Main;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// Check the command type
		if (event.getName().equals("ping") && !event.getUser().isBot()) {
			// Send waiting message
			event.deferReply().queue();

			Main.log("-> Command \">ping\" executed by " + event.getUser().getName());

			// Build embed
			EmbedBuilder eb = new EmbedBuilder();

			// Set embed color
			eb.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));

			try {
				event.getJDA().getRestPing().queue((time) -> {
					eb.setDescription("Ping: " + time + "ms");
					MessageEmbed embed = eb.build();

					event.getHook().sendMessageEmbeds(embed).queue();
				});
			} catch (Exception e) {
			}
		}
	}
}
